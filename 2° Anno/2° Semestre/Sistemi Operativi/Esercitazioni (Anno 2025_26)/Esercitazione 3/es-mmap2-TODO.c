#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <ctype.h>

int main(int argc, char *argv[]) {

    if (argc != 2) {
        fprintf(stderr, "Uso: %s <file>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    char *filename = argv[1];

    /*
     * TODO 1
     *
     * Aprire il file in lettura/scrittura.
     */

    int fd = /* TODO */;

    if (fd < 0) {
        perror("open");
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 2
     *
     * Ottenere la dimensione del file.
     */

    off_t size = /* TODO */;

    if (size < 0) {
        perror("lseek");
        close(fd);
        exit(EXIT_FAILURE);
    }

    if (size == 0) {
        close(fd);
        return 0;
    }

    /*
     * TODO 3
     *
     * Mappare il file in memoria.
     *
     * Suggerimenti:
     * - PROT_READ | PROT_WRITE
     * - MAP_SHARED
     */

    char *data = /* TODO */;

    if (data == MAP_FAILED) {
        perror("mmap");
        close(fd);
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 4
     *
     * Convertire tutte le lettere minuscole
     * in maiuscole direttamente nella memoria mappata.
     */

    for (off_t i = 0; i < size; i++) {

        /*
         * TODO
         */

    }

    /*
     * TODO 5
     *
     * Liberare la memoria mappata.
     */

    if (/* TODO */ < 0) {
        perror("munmap");
        close(fd);
        exit(EXIT_FAILURE);
    }

    close(fd);

    return 0;
}