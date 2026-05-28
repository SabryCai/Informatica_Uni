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
     * Aprire il file in sola lettura.
     */

    int fd = /* TODO */;

    if (fd < 0) {
        perror("open");
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 2
     *
     * Spostarsi alla fine del file usando lseek()
     * e salvare la dimensione del file in size.
     */

    off_t size = /* TODO */;

    if (size < 0) {
        perror("lseek");
        close(fd);
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 3
     *
     * Leggere il file al contrario:
     *
     * - partire dall'ultimo byte
     * - spostarsi con lseek()
     * - leggere un carattere con read()
     * - stamparlo su stdout
     */

    char c;

    for (off_t i = size - 1; i >= 0; i--) {

        /*
         * TODO:
         * spostare l'offset alla posizione i
         */

        if (/* TODO */ < 0) {
            perror("lseek");
            close(fd);
            exit(EXIT_FAILURE);
        }

        /*
         * TODO:
         * leggere un byte
         */

        if (/* TODO */ != 1) {
            perror("read");
            close(fd);
            exit(EXIT_FAILURE);
        }

        /*
         * TODO:
         * stampare il carattere letto
         */

        /* TODO */
    }

    write(STDOUT_FILENO, "\n", 1);

    close(fd);

    return 0;
}