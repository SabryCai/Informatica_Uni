#include <sys/socket.h>
#include <sys/un.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

#define SOCKET_PATH "/tmp/sum_bin_socket"

/*
 * TODO:
 * Implementare read_all().
 */
int read_all(int fd, void *buf, size_t count) {
    /* TODO */
    return 0;
}

/*
 * TODO:
 * Implementare write_all().
 */
int write_all(int fd, const void *buf, size_t count) {
    /* TODO */
    return 0;
}

int main(void) {
    int sd;
    struct sockaddr_un srv_addr;

    uint32_t valori[] = {10, 20, 30, 40, 50};
    uint32_t n = sizeof(valori) / sizeof(valori[0]);

    /*
     * TODO 1:
     * Creare un socket locale stream.
     */

    /*
     * TODO 2:
     * Inizializzare l'indirizzo del server.
     */

    /*
     * TODO 3:
     * Connettersi al server.
     */

    /*
     * TODO 4:
     * Convertire n in network byte order.
     */

    /*
     * TODO 5:
     * Inviare n al server con write_all().
     */

    /*
     * TODO 6:
     * Per ogni elemento dell'array:
     * - convertirlo con htonl()
     * - inviarlo con write_all()
     */

    /*
     * TODO 7:
     * Leggere la somma dal server.
     */

    /*
     * TODO 8:
     * Convertire la somma con ntohl().
     */

    /*
     * TODO 9:
     * Stampare la somma.
     */

    /*
     * TODO 10:
     * Chiudere il socket.
     */

    return 0;
}