import socket
import time

# Server configuration.
hostName = '10.20.8.105'
serverPort = 12000

# Create a UDP socket.
clientSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
clientSocket.settimeout(1)  # Set timeout to 1 second.

# Loop through sending message 10 times to ping server 10 times.
for seq in range(1, 11):
    send_time = time.time()
    message = f'Ping {seq} {send_time}'
    # Try to send message, catch timeout exceptions after 1 second.
    try:
        # Send the message to hostName at serverPort.
        clientSocket.sendto(message.encode(), (hostName, serverPort))
        
        # Attempt to receive response.
        data, server_address = clientSocket.recvfrom(1024)
        recv_time = time.time()
        rtt = recv_time - send_time
        
        # Print the response and RTT.
        print(f'Reply from {server_address}: {data.decode()}')
        print(f'RTT: {rtt:.6f} seconds\n') # Adjust for float value to 6 decimal places.
    except socket.timeout:
        # Handle timeout
        print(f'Request {seq} timed out\n')

clientSocket.close()