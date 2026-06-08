#include <sys/socket.h>
#include <sys/un.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define SOCKET_PATH "/tmp/sum_socket"
#define BUF_SIZE 1024

int main(void) {
    int sd;
    struct sockaddr_un srv_addr;

    int valori[] = {10, 20, 30, 40, 50};
    int n = 5;

    char msg[BUF_SIZE] = "";
    char temp[32];

    /*
     * TODO 1:
     * Convertire l'array valori in una stringa.
     * Esempio:
     * {10, 20, 30} -> "10 20 30\n"
     */

    /*
     * TODO 2:
     * Creare un socket locale stream.
     */

    /*
     * TODO 3:
     * Inizializzare srv_addr con:
     * - AF_LOCAL
     * - SOCKET_PATH
     */

    /*
     * TODO 4:
     * Connettersi al server con connect().
     */

    /*
     * TODO 5:
     * Inviare msg al server con write().
     */

    /*
     * TODO 6:
     * Leggere la risposta dal server.
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