#include <sys/socket.h>
#include <sys/un.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define SOCKET_PATH "/tmp/sum_socket"
#define BUF_SIZE 1024

int calcola_somma(char *msg) {
    int somma = 0;

    /*
     * TODO:
     * Usare strtok() per dividere msg in token separati da spazio/newline.
     * Convertire ogni token con atoi().
     * Accumulare il risultato in somma.
     */

    return somma;
}

int main(void) {
    int listen_sd, client_sd;
    struct sockaddr_un srv_addr;
    char buffer[BUF_SIZE];

    /*
     * TODO 1:
     * Creare un socket locale stream:
     * socket(AF_LOCAL, SOCK_STREAM, 0)
     */

    /*
     * TODO 2:
     * Inizializzare srv_addr:
     * - memset()
     * - sun_family = AF_LOCAL
     * - sun_path = SOCKET_PATH
     */

    /*
     * TODO 3:
     * Rimuovere un eventuale socket precedente:
     * unlink(SOCKET_PATH)
     */

    /*
     * TODO 4:
     * Fare bind() del socket.
     */

    /*
     * TODO 5:
     * Mettere il socket in ascolto con listen().
     */

    printf("Server in ascolto su %s\n", SOCKET_PATH);

    while (1) {
        /*
         * TODO 6:
         * Accettare una connessione con accept().
         */

        /*
         * TODO 7:
         * Leggere dal client la stringa contenente gli interi.
         */

        /*
         * TODO 8:
         * Terminare la stringa ricevuta con '\0'.
         */

        /*
         * TODO 9:
         * Calcolare la somma.
         */

        /*
         * TODO 10:
         * Convertire la somma in stringa con snprintf().
         */

        /*
         * TODO 11:
         * Inviare la risposta al client con write().
         */

        /*
         * TODO 12:
         * Chiudere il socket del client.
         */
    }

    close(listen_sd);
    unlink(SOCKET_PATH);

    return 0;
}