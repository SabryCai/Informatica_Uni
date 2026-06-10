#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fcntl.h>
#include <string.h>
#include <errno.h>
#include <sys/wait.h>

#define CGROUP_ROOT "/sys/fs/cgroup"
#define CGROUP_NAME "demo_cgroup"
#define CGROUP_PATH CGROUP_ROOT "/" CGROUP_NAME

static int write_file(const char *path, const char *value) {
    int fd = open(path, O_WRONLY | O_CLOEXEC);
    if (fd < 0) {
        fprintf(stderr, "open(%s) failed: %s\n", path, strerror(errno));
        return -1;
    }
    ssize_t len = strlen(value);
    if (write(fd, value, len) != len) {
        fprintf(stderr, "write(%s) failed: %s\n", path, strerror(errno));
        close(fd);
        return -1;
    }
    close(fd);
    return 0;
}

int main(void) {
    // 1) Controllo che esista la root di cgroup v2
    struct stat st;
    if (stat(CGROUP_ROOT, &st) == -1) {
        perror("stat(" CGROUP_ROOT ")");
        fprintf(stderr, "Assicurati che cgroup v2 sia montato in " CGROUP_ROOT "\n");
        return 1;
    }

    // 2) Creo il cgroup: /sys/fs/cgroup/demo_cgroup
    if (mkdir(CGROUP_PATH, 0755) == -1 && errno != EEXIST) {
        perror("mkdir(" CGROUP_PATH ")");
        return 1;
    }

    printf("[parent] cgroup creato: %s\n", CGROUP_PATH);

    pid_t pid = fork();
    if (pid < 0) {
        perror("fork");
        return 1;
    }

    if (pid == 0) {
        // === FIGLIO ===
        printf("[child] PID figlio (host): %d\n", getpid());
        printf("[child] Attendo che il padre mi metta nel cgroup...\n");
        sleep(2); // giusto per far tempo al padre

        printf("[child] Provo ad allocare memoria in un loop...\n");
        const size_t step = 10 * 1024 * 1024; // 10 MB
        size_t total = 0;
        while (1) {
            void *p = malloc(step);
            if (!p) {
                perror("[child] malloc");
                break;
            }
            memset(p, 0, step);
            total += step;
            printf("[child] allocati ~%zu MB\n", total / (1024 * 1024));
            sleep(1);
        }
        return 0;
    }

    // === PADRE ===
    printf("[parent] PID figlio: %d\n", pid);

    // 3) Sposto il figlio nel nuovo cgroup
    char procs_path[] = CGROUP_PATH "/cgroup.procs";
    char buf[32];
    snprintf(buf, sizeof(buf), "%d\n", pid);

    if (write_file(procs_path, buf) < 0) {
        fprintf(stderr, "[parent] errore nello spostare il processo nel cgroup\n");
        kill(pid, SIGKILL);
        return 1;
    }
    printf("[parent] Spostato PID %d in %s\n", pid, CGROUP_PATH);

    // 4) Imposto un limite di memoria, es. 50 MB
    //    cgroup v2 vuole un numero di byte o la parola "max".
    const char *mem_limit = "52428800\n"; // 50 * 1024 * 1024
    const char *mem_path = CGROUP_PATH "/memory.max";

    if (write_file(mem_path, mem_limit) < 0) {
        fprintf(stderr, "[parent] impossibile impostare memory.max\n");
        kill(pid, SIGKILL);
        return 1;
    }
    printf("[parent] Impostato memory.max = 50MB per %s\n", CGROUP_PATH);

    // (opzionale) limite sul numero di processi:
    // write_file(CGROUP_PATH "/pids.max", "10\n");

    // 5) Attendo il figlio
    int status;
    waitpid(pid, &status, 0);
    printf("[parent] Figlio terminato con status %d\n", status);

    return 0;
}
