#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define PORT 5200
#define BUF_SIZE 1024

int calcola_somma(char *msg) {
    int somma = 0;

    /*
     * TODO:
     * Dividere msg in token separati da spazi/newline con strtok().
     * Convertire ogni token in intero con atoi().
     * Accumulare la somma.
     */

    return somma;
}

int main(void) {
    int s, c;
    struct sockaddr_in addr = {0};
    char buffer[BUF_SIZE];

    /*
     * TODO 1:
     * Inizializzare addr:
     * - sin_family = AF_INET
     * - sin_port = htons(PORT)
     * - sin_addr.s_addr = htonl(INADDR_ANY)
     */

    /*
     * TODO 2:
     * Creare il socket TCP IPv4:
     * socket(AF_INET, SOCK_STREAM, 0)
     */

    /*
     * TODO 3:
     * Fare bind().
     */

    /*
     * TODO 4:
     * Mettere il socket in ascolto con listen().
     */

    printf("Server TCP in ascolto sulla porta %d\n", PORT);

    while (1) {
        /*
         * TODO 5:
         * Accettare una connessione con accept().
         */

        /*
         * TODO 6:
         * Leggere il messaggio dal client con read().
         */

        /*
         * TODO 7:
         * Terminare la stringa ricevuta con '\0'.
         */

        /*
         * TODO 8:
         * Calcolare la somma.
         */

        /*
         * TODO 9:
         * Convertire la somma in stringa con snprintf().
         */

        /*
         * TODO 10:
         * Inviare la risposta al client con write().
         */

        /*
         * TODO 11:
         * Chiudere il socket connesso al client.
         */
    }

    close(s);
    return 0;
}