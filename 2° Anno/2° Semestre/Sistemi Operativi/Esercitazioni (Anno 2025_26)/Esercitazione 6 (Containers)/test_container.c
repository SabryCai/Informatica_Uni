#define _GNU_SOURCE
#include <sched.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/mount.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/utsname.h>
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#define STACK_SIZE (1024 * 1024)

static int child_main(void *arg) {
    (void)arg;

    printf("[child] Sono nel child, PID visto da qui: %d\n", getpid());

    // 1) Cambia hostname (UTS namespace)
    const char *new_hostname = "nsdemo";
    if (sethostname(new_hostname, strlen(new_hostname)) == -1) {
        perror("[child] sethostname");
        return 1;
    }

    // 2) Assicuriamoci che /proc esista
    if (mkdir("/proc", 0555) == -1 && errno != EEXIST) {
        perror("[child] mkdir /proc");
        return 1;
    }

    // 3) Rimontiamo /proc nel nuovo mount namespace
    //    Così i PID che vediamo sono quelli del nuovo PID namespace.
    if (mount("proc", "/proc", "proc", 0, "") == -1) {
        perror("[child] mount /proc");
        return 1;
    }

    // Info di debug
    struct utsname uts;
    if (uname(&uts) == 0) {
        printf("[child] Hostname nel namespace: %s\n", uts.nodename);
    }

    printf("[child] Esecuzione di /bin/sh dentro il namespace.\n");
    printf("        Prova questi comandi:\n");
    printf("          - hostname\n");
    printf("          - ps aux\n");
    printf("          - ls /proc\n");
    printf("\n");

    // 4) Avvia una shell interattiva dentro il namespace
    char *const args[] = { "/bin/sh", NULL };
    execvp(args[0], args);

    // Se exec fallisce:
    perror("[child] execvp");
    return 1;
}

int main(void) {
    // Stack per il child (clone richiede uno stack esplicito)
    char *stack = malloc(STACK_SIZE);
    if (!stack) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }
    char *stack_top = stack + STACK_SIZE;

    printf("[parent] PID del processo principale (host): %d\n", getpid());
    printf("[parent] Creo un 'mini container' con PID+UTS+MOUNT(+NET) namespace...\n");

    // Flag per clone:
    // - CLONE_NEWPID : nuovo PID namespace
    // - CLONE_NEWUTS : nuovo hostname
    // - CLONE_NEWNS  : nuovo mount namespace
    // - CLONE_NEWNET : nuovo network namespace (opzionale ma carino)
    // - SIGCHLD      : il figlio invia SIGCHLD alla terminazione (comportamento normale)
    int flags = CLONE_NEWPID | CLONE_NEWUTS | CLONE_NEWNS | CLONE_NEWNET | SIGCHLD;

    pid_t child_pid = clone(child_main, stack_top, flags, NULL);
    if (child_pid == -1) {
        perror("clone");
        free(stack);
        exit(EXIT_FAILURE);
    }

    printf("[parent] clone() ha creato un child con PID host: %d\n", child_pid);
    printf("[parent] Dentro il nuovo PID namespace il child sarà PID 1.\n\n");

    // Attende la fine del child (la /bin/sh nel namespace)
    int status;
    if (waitpid(child_pid, &status, 0) == -1) {
        perror("waitpid");
        free(stack);
        exit(EXIT_FAILURE);
    }

    printf("[parent] Il child è terminato con status %d\n", status);
    free(stack);
    return 0;
}
