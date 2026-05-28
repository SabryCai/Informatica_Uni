#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>

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
     * Ottenere la dimensione del file usando lseek().
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
     * - protezione: PROT_READ
     * - modalità: MAP_PRIVATE
     * - offset: 0
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
     * Stampare il contenuto del file su stdout.
     * Non usare read().
     */

    if (/* TODO */ < 0) {
        perror("write");
        munmap(data, size);
        close(fd);
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 5
     *
     * Liberare la mappatura.
     */

    if (/* TODO */ < 0) {
        perror("munmap");
        close(fd);
        exit(EXIT_FAILURE);
    }

    close(fd);

    return 0;
}