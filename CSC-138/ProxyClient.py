from socket import *

# Create serverName and serverPort for local machine.
serverName = 'localhost'
serverPort = 12000

# Create client socket and connect to local machine.
clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort))

# Generate get request for index html page of website in browser.
request = "GET /index.html HTTP/1.0\r\n\r\n"
clientSocket.send(request.encode())

# Receive response and print to terminal in client.
response = clientSocket.recv(4096).decode()
print(response)

# Close client socket.
clientSocket.close()