#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/wait.h>

#define BUF_SIZE 128

int main(int argc, char *argv[]) {

    if (argc != 3) {
        fprintf(stderr, "Uso: %s <file> <stringa>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    char *filename = argv[1];
    char *msg = argv[2];

    /*
     * TODO 1
     *
     * Aprire il file in lettura/scrittura.
     *
     * Requisiti:
     * - creare il file se non esiste
     * - svuotarlo se esiste già
     *
     * Salvare il file descriptor in fd.
     */

    int fd = open(
        /* TODO */
    );

    if (fd < 0) {
        perror("open");
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 2
     *
     * Creare un processo figlio.
     */

    pid_t pid = /* TODO */;

    if (pid < 0) {
        perror("fork");
        close(fd);
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {

        /*
         * FIGLIO
         */

        /*
         * TODO 3
         *
         * Scrivere msg nel file.
         */

        ssize_t n = write(
            /* TODO */
        );

        if (n < 0) {
            perror("write");
            close(fd);
            exit(EXIT_FAILURE);
        }

        close(fd);
        exit(EXIT_SUCCESS);
    }

    /*
     * PADRE
     */

    /*
     * TODO 4
     *
     * Aspettare la terminazione del figlio.
     */

    /* TODO */

    /*
     * TODO 5
     *
     * Tornare all'inizio del file.
     */

    if (lseek(fd, 0, SEEK_SET) < 0) {
        perror("lseek");
        close(fd);
        exit(EXIT_FAILURE);
    }

    char buffer[BUF_SIZE];

    /*
     * TODO 6
     *
     * Leggere dal file e stampare su stdout.
     */

    ssize_t nr = read(
        /* TODO */
    );

    if (nr < 0) {
        perror("read");
        close(fd);
        exit(EXIT_FAILURE);
    }

    write(STDOUT_FILENO, buffer, nr);
    write(STDOUT_FILENO, "\n", 1);

    close(fd);

    return 0;
}