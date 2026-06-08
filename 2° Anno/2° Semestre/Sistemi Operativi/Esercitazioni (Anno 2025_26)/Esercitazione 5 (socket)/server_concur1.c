#include <arpa/inet.h>
#include <errno.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

#define PORT 5201
#define BACKLOG 8

static void handle_client(int fd) {
    char buf[1024];

    for (;;) {
        ssize_t n = recv(fd, buf, sizeof(buf), 0);

        if (n == 0)
            break;              // client ha chiuso

        if (n < 0) {
            if (errno == EINTR)
                continue;
            perror("recv");
            break;
        }

        // echo
        ssize_t off = 0;
        while (off < n) {
            ssize_t w = send(fd, buf + off, (size_t)(n - off), 0);

            if (w < 0) {
                if (errno == EINTR)
                    continue;
                perror("send");
                close(fd);
                _exit(1);
            }

            off += w;
        }
    }

    close(fd);
    _exit(0);   // il figlio termina qui
}

int main(void) {
    int s = socket(AF_INET, SOCK_STREAM, 0);
    if (s < 0) {
        perror("socket");
        return 1;
    }

    struct sockaddr_in addr = {0};
    addr.sin_family = AF_INET;
    addr.sin_port = htons(PORT);
    addr.sin_addr.s_addr = htonl(INADDR_ANY);

    if (bind(s, (struct sockaddr *)&addr, sizeof(addr)) < 0) {
        perror("bind");
        return 1;
    }

    if (listen(s, BACKLOG) < 0) {
        perror("listen");
        return 1;
    }

    printf("Server in ascolto su 0.0.0.0:%d (fork per connessione)\n", PORT);

    for (;;) {
        int c = accept(s, NULL, NULL);

        if (c < 0) {
            if (errno == EINTR)
                continue;
            perror("accept");
            continue;
        }

        pid_t pid = fork();

        if (pid < 0) {
            perror("fork");
            close(c);
            continue;
        }

        if (pid == 0) {
            // FIGLIO
            close(s);              // al figlio non serve la listening socket
            handle_client(c);      // non ritorna, figli restano zombie
        }

        close(c);                  // il padre torna ad accettare altri client
    }
}