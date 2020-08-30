package chatserver1.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

 // chatserver1.client to connect to ChatServer1

public class ChatClient {
    private Scanner console;			// input from the user
    private ChatClientThread serverConnection;		// communication with the chatserver1.server
    private boolean disconnected = false;		// chatserver1.server status

    public ChatClient(Socket sock) throws IOException {

        // For reading lines from the console
        console = new Scanner(System.in);

        // Fire off a new thread to handle incoming messages from chatserver1.server
        serverConnection = new ChatClientThread(sock, this);
        serverConnection.setDaemon(true);
        serverConnection.start();

        // Greeting; name request and response
        System.out.println("Please enter your name: ");
        String name = console.nextLine(); //blocks until keyboard input
        serverConnection.send(name);
    }

    // Get console input and send it to chatserver1.server;

    public void handleClientConnection() {

        while (!disconnected) {

            serverConnection.send(console.nextLine());
        }
    }

    // Handles the chatserver1.client connection status

    public void disconnect() {
        disconnected = true;
    }

    public static void main(String[] args) throws IOException {
        new ChatClient(new Socket("127.0.0.1",8080)).handleClientConnection();
    }
}
