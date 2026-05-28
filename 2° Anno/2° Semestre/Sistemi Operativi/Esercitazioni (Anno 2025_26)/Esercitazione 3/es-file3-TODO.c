#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

#define HOLE_SIZE 10000

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
     * Scrivere la stringa "INIZIO\n".
     */

    const char *start = "INIZIO\n";

    if (/* TODO */ < 0) {
        perror("write");
        close(fd);
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 3
     *
     * Creare un buco nel file usando lseek().
     *
     * Suggerimento:
     * spostarsi in avanti di HOLE_SIZE byte
     * rispetto alla posizione corrente.
     */

    if (/* TODO */ < 0) {
        perror("lseek");
        close(fd);
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 4
     *
     * Scrivere la stringa "FINE\n".
     */

    const char *end = "FINE\n";

    if (/* TODO */ < 0) {
        perror("write");
        close(fd);
        exit(EXIT_FAILURE);
    }

    close(fd);

    printf("File creato.\n");

    return 0;
}