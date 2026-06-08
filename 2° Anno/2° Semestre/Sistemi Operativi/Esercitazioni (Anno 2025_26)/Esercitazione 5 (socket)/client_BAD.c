#include <arpa/inet.h>
#include <unistd.h>
#include <stdio.h>
#include <stdint.h>

int main(void) {
    int s = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in addr = {0};
    addr.sin_family = AF_INET;
    addr.sin_port = htons(5000);
    inet_pton(AF_INET, "127.0.0.1", &addr.sin_addr);

    connect(s, (struct sockaddr *)&addr, sizeof(addr));

    uint32_t x = htonl(41);

    send(s, &x, sizeof(x), 0);      // NON robusto
    recv(s, &x, sizeof(x), 0);      // NON robusto

    printf("Risposta: %u\n", ntohl(x));

    close(s);
    return 0;
}