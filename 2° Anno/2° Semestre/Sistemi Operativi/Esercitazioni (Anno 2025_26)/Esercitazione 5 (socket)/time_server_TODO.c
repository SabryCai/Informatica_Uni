#include <sys/socket.h>
#include <sys/un.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>

#define SOCKET_PATH "/tmp/time_socket"

/*
 * TODO: implementare questa funzione.
 *
 * La funzione deve:
 * 1. ottenere l'ora corrente con time()
 * 2. convertirla in stringa con ctime_r()
 * 3. inviare la stringa al client usando write()
 */
void gestisci(int sd) {
    char buffer[26];
    time_t ora;

    /* TODO */
}

int main(void) {
    int listen_sd, connect_sd;
    struct sockaddr_un my_addr, client_addr;
    socklen_t client_len;

    /*
     * TODO 1:
     * Creare un socket locale di tipo stream.
     * Suggerimento:
     * socket(AF_LOCAL, SOCK_STREAM, 0)
     */

    /*
     * TODO 2:
     * Inizializzare la struct sockaddr_un del server.
     * Campi da impostare:
     * - sun_family
     * - sun_path
     */

    /*
     * TODO 3:
     * Rimuovere un eventuale socket precedente con unlink().
     */

    /*
     * TODO 4:
     * Associare il socket all'indirizzo con bind().
     */

    /*
     * TODO 5:
     * Mettere il socket in ascolto con listen().
     */

    printf("Server in ascolto su %s\n", SOCKET_PATH);

    while (1) {
        /*
         * TODO 6:
         * Accettare una nuova connessione con accept().
         */

        printf("Nuova connessione accettata.\n");

        /*
         * TODO 7:
         * Gestire il client inviando l'ora corrente.
         */

        /*
         * TODO 8:
         * Chiudere il socket connesso al client.
         */
    }

    /*
     * TODO 9:
     * Chiudere il socket di ascolto e rimuovere il file socket.
     */

    return 0;
}