#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/mman.h>
#include <sys/wait.h>

#define SHARED_SIZE 128

int main(int argc, char *argv[]) {

    if (argc != 3) {
        fprintf(stderr, "Uso: %s <file> <messaggio>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    char *filename = argv[1];
    char *msg = argv[2];

    /*
     * TODO 1
     *
     * Aprire/creare il file in lettura/scrittura.
     */

    int fd = /* TODO */;

    if (fd < 0) {
        perror("open");
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 2
     *
     * Impostare la dimensione del file a SHARED_SIZE
     * usando ftruncate().
     */

    if (/* TODO */ < 0) {
        perror("ftruncate");
        close(fd);
        exit(EXIT_FAILURE);
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
     * Creare un processo figlio.
     */

    pid_t pid = /* TODO */;

    if (pid < 0) {
        perror("fork");
        munmap(data, SHARED_SIZE);
        close(fd);
        exit(EXIT_FAILURE);
    }

    if (pid == 0) {

        /*
         * FIGLIO
         */

        /*
         * TODO 5
         *
         * Scrivere msg nella memoria condivisa.
         */

        /* TODO */

        munmap(data, SHARED_SIZE);
        close(fd);

        exit(EXIT_SUCCESS);
    }

    /*
     * PADRE
     */

    wait(NULL);

    /*
     * TODO 6
     *
     * Stampare il contenuto della memoria condivisa.
     */

    printf("Messaggio ricevuto: %s\n",
           /* TODO */);

    /*
     * TODO 7
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