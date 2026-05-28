#include <stdio.h>
#include <stdlib.h>
#include <sys/ioctl.h>
#include <unistd.h>

int main(int argc, char *argv[]) {

    struct winsize w;

    // Controllo argomenti
    if (argc != 3) {
        printf("Uso: %s <righe> <colonne>\n", argv[0]);
        return 1;
    }

    int rows = atoi(argv[1]);
    int cols = atoi(argv[2]);

    // TODO 1:
    // usare ioctl() per leggere
    // la dimensione del terminale

    printf("Terminale: %d righe x %d colonne\n",
           w.ws_row, w.ws_col);

    // TODO 2:
    // verificare se la matrice entra nel terminale
    //
    // Suggerimento:
    // ogni numero occupa circa 4 caratteri

    // TODO 3:
    // se il terminale è troppo piccolo:
    // - stampare messaggio
    // - chiedere resize
    // - attendere ENTER
    // - terminare

    // TODO 4:
    // stampare una matrice rows x cols

    return 0;
}