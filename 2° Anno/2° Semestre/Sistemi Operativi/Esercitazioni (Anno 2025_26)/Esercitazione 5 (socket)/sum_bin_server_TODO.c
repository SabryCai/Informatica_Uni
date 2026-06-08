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
 * Implementare una read robusta.
 *
 * read() può leggere meno byte di quelli richiesti.
 * Questa funzione deve continuare a leggere finché non ha ricevuto
 * esattamente count byte.
 */
int read_all(int fd, void *buf, size_t count) {
    /* TODO */
    return 0;
}

/*
 * TODO:
 * Implementare una write robusta.
 *
 * write() può scrivere meno byte di quelli richiesti.
 * Questa funzione deve continuare a scrivere finché non ha inviato
 * esattamente count byte.
 */
int write_all(int fd, const void *buf, size_t count) {
    /* TODO */
    return 0;
}

void gestisci_client(int client_sd) {
    uint32_t n_net;
    uint32_t n;
    uint32_t somma = 0;

    /*
     * TODO 1:
     * Leggere dal client il numero di elementi n.
     * Attenzione: n arriva in network byte order.
     */

    /*
     * TODO 2:
     * Convertire n in host byte order con ntohl().
     */

    /*
     * TODO 3:
     * Leggere n valori uint32_t dal client.
     * Per ogni valore:
     * - leggere 4 byte
     * - convertire con ntohl()
     * - aggiungere alla somma
     */

    /*
     * TODO 4:
     * Convertire la somma in network byte order con htonl().
     */

    /*
     * TODO 5:
     * Inviare la somma al client usando write_all().
     */
}

int main(void) {
    int listen_sd, client_sd;
    struct sockaddr_un srv_addr;

    /*
     * TODO 6:
     * Creare un socket locale stream.
     */

    /*
     * TODO 7:
     * Inizializzare srv_addr:
     * - memset()
     * - sun_family = AF_LOCAL
     * - sun_path = SOCKET_PATH
     */

    /*
     * TODO 8:
     * Rimuovere un eventuale socket precedente con unlink().
     */

    /*
     * TODO 9:
     * Fare bind().
     */

    /*
     * TODO 10:
     * Mettere il socket in ascolto con listen().
     */

    printf("Server in ascolto su %s\n", SOCKET_PATH);

    while (1) {
        /*
         * TODO 11:
         * Accettare una connessione con accept().
         */

        /*
         * TODO 12:
         * Gestire il client.
         */

        /*
         * TODO 13:
         * Chiudere il socket del client.
         */
    }

    close(listen_sd);
    unlink(SOCKET_PATH);

    return 0;
}