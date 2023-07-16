#include <sys/socket.h>
#include <netinet/in.h>
#include <linux/vm_sockets.h>
#include <string.h>
#include <arpa/inet.h>
#include <stdio.h>

int main() {
	int sock = socket(AF_INET, SOCK_STREAM, 0);
	if(sock < 0) {
		printf("Failed to create vsock socket\n");
		return 1;
	}
	printf("Create vsock socket susccefully\n");

	struct sockaddr_in addr = (struct sockaddr_in) {
		.sin_family = AF_INET,
		.sin_port = htons(9999),
		.sin_addr.s_addr = inet_addr("127.0.0.1"),
	};

	int connection = connect(sock, (struct sockaddr*)& addr, sizeof(addr));
	if(connection < 0) {
		printf("Failed to accept vsock connection\n");
		return 1;
	}
	printf("Accepted vsock connection\n");

	char input[] = "Hello Server!";
	int ret = send(sock, input, sizeof(input), 0);
	if(ret < 0) {
		printf("Failed to send data\n");
		return 1;
	}
	printf("Send data susccefully\n");

	int close = shutdown(sock, SHUT_RDWR);
	if(close < 0) {
		printf("Failed to close socket\n");
		return 1;
	}
	printf("Close socket\n");

    return 0;
}
