#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

int main(int argc, char *argv[]) {

    if (argc != 2) {
        fprintf(stderr, "Uso: %s <file>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    char *filename = argv[1];

    /*
     * TODO 1
     *
     * Aprire il file in scrittura:
     * - creare il file se non esiste
     * - svuotarlo se esiste già
     */

    int fd = /* TODO */;

    if (fd < 0) {
        perror("open");
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 2
     *
     * Redirigere stdout sul file usando dup2().
     */

    if (/* TODO */ < 0) {
        perror("dup2");
        close(fd);
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 3
     *
     * Chiudere il file descriptor originale.
     */

    /* TODO */

    /*
     * Questa printf deve scrivere nel file.
     */

    printf("Hello world!\n");

    return 0;
}