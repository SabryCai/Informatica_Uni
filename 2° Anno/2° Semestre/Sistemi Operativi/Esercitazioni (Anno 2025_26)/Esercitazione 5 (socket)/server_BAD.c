#include <arpa/inet.h>
#include <unistd.h>
#include <stdio.h>
#include <stdint.h>

int main(void) {
    int s = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in addr = {0};
    addr.sin_family = AF_INET;
    addr.sin_port = htons(5000);
    addr.sin_addr.s_addr = INADDR_ANY;

    bind(s, (struct sockaddr *)&addr, sizeof(addr));
    listen(s, 1);

    int c = accept(s, NULL, NULL);

    uint32_t x;
    recv(c, &x, sizeof(x), 0);      // NON robusto

    x = ntohl(x);
    x++;
    x = htonl(x);

    send(c, &x, sizeof(x), 0);      // NON robusto

    close(c);
    close(s);
    return 0;
}