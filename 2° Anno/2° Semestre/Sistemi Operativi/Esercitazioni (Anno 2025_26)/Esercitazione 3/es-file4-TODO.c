#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <errno.h>

#define BUF_SIZE 128

int main(int argc, char *argv[]) {

    if (argc != 5) {
        fprintf(stderr, "Uso: %s <file1> <file2> <n> <m>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    char *file1 = argv[1];
    char *file2 = argv[2];

    /*
     * TODO 1
     *
     * Convertire n ed m in interi.
     */

    int n = /* TODO */;
    int m = /* TODO */;

    /*
     * TODO 2
     *
     * Verificare:
     * - n >= 0
     * - m >= 0
     * - n <= m
     */

    if (/* TODO */) {
        fprintf(stderr, "Intervallo non valido\n");
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 3
     *
     * Aprire file1 in lettura.
     */

    int fd1 = /* TODO */;

    if (fd1 < 0) {
        perror("open file1");
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 4
     *
     * Aprire file2:
     * - in scrittura
     * - crearlo se non esiste
     * - fallire se esiste già
     */

    int fd2 = /* TODO */;

    if (fd2 < 0) {
        perror("open file2");
        close(fd1);
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 5
     *
     * Ottenere la dimensione di file1 usando lseek().
     */

    off_t size = /* TODO */;

    if (size < 0) {
        perror("lseek");
        close(fd1);
        close(fd2);
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 6
     *
     * Verificare che m sia minore della dimensione del file.
     */

    if (/* TODO */) {
        fprintf(stderr, "m supera la dimensione del file\n");
        close(fd1);
        close(fd2);
        exit(EXIT_FAILURE);
    }

    /*
     * TODO 7
     *
     * Spostarsi al byte n.
     */

    if (/* TODO */ < 0) {
        perror("lseek");
        close(fd1);
        close(fd2);
        exit(EXIT_FAILURE);
    }

    int bytes_to_copy = m - n + 1;

    char buffer[BUF_SIZE];

    /*
     * TODO 8
     *
     * Copiare i byte da file1 a file2 usando
     * read() e write().
     */

    while (bytes_to_copy > 0) {

        int chunk =
            bytes_to_copy < BUF_SIZE ?
            bytes_to_copy :
            BUF_SIZE;

        ssize_t nr = read(fd1, buffer, chunk);

        if (nr < 0) {
            perror("read");
            close(fd1);
            close(fd2);
            exit(EXIT_FAILURE);
        }

        if (nr == 0) {
            break;
        }

        if (/* TODO */ < 0) {
            perror("write");
            close(fd1);
            close(fd2);
            exit(EXIT_FAILURE);
        }

        bytes_to_copy -= nr;
    }

    close(fd1);
    close(fd2);

    return 0;
}