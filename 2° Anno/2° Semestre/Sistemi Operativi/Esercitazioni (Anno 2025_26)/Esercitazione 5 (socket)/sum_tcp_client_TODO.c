#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define PORT 5200
#define SERVER_IP "127.0.0.1"
#define BUF_SIZE 1024

int main(void) {
    int s;
    struct sockaddr_in addr = {0};

    int valori[] = {10, 20, 30, 40, 50};
    int n = 5;

    char msg[BUF_SIZE] = "";
    char temp[32];

    /*
     * TODO 1:
     * Inizializzare addr:
     * - sin_family = AF_INET
     * - sin_port = htons(PORT)
     * - indirizzo IP con inet_pton()
     */

    /*
     * TODO 2:
     * Convertire l'array in stringa.
     * Esempio:
     * {10, 20, 30} -> "10 20 30\n"
     */

    /*
     * TODO 3:
     * Creare il socket TCP IPv4.
     */

    /*
     * TODO 4:
     * Connettersi al server con connect().
     */

    /*
     * TODO 5:
     * Inviare il messaggio al server con write().
     */

    /*
     * TODO 6:
     * Leggere la risposta dal server con read().
     */

    /*
     * TODO 7:
     * Terminare la risposta con '\0' e stamparla.
     */

    /*
     * TODO 8:
     * Chiudere il socket.
     */

    return 0;
}