#include <sys/socket.h>
#include <sys/un.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#define SOCKET_PATH "/tmp/time_socket"

int main(void) {
    int sd;
    struct sockaddr_un srv_addr;
    char msg[100];

    /*
     * TODO 1:
     * Creare un socket locale di tipo stream.
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
     * Connettersi al server con connect().
     */

    /*
     * TODO 4:
     * Ricevere il messaggio dal server usando read().
     * Ricordarsi di terminare la stringa con '\0'.
     */

    /*
     * TODO 5:
     * Stampare l'ora ricevuta.
     */

    /*
     * TODO 6:
     * Chiudere il socket.
     */

    return 0;
}