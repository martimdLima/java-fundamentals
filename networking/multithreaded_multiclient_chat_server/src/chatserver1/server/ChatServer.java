package chatserver1.server;

import java.net.*;
import java.util.*;
import java.io.*;


public class ChatServer {
    private ServerSocket serverSocket;						// for accepting connections
    private ArrayList<ChatServerThread> clientConnections;	// all the connections with clients

    public ChatServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        clientConnections = new ArrayList<>();
    }

    // The usual loop of accepting connections and firing off new threads to handle them

    public void getConnections() throws IOException {

        while (true) {
            //serverSocket.accept in next line blocks until new connection

            ChatServerThread clientConnection = new ChatServerThread(serverSocket.accept(), this);
            clientConnection.setDaemon(true);
            clientConnection.start();
            addClientConnection(clientConnection);
        }
    }

    // Adds the handler to the list of current chatserver1.client handlers

    public synchronized void addClientConnection(ChatServerThread connection) {
        clientConnections.add(connection);
    }

    // Removes the handler from the list of current chatserver1.client handlers

    public synchronized void removeClientConnection(ChatServerThread connection) {
        clientConnections.remove(connection);
    }

    // Sends the message from the one chatserver1.client handler to all the others, except the recipient

    public synchronized void broadcast(ChatServerThread recipient, String message) {

        for (ChatServerThread clientConnection : clientConnections) {

            if (clientConnection != recipient) {
                clientConnection.send(message);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("waiting for connections");
        new ChatServer(new ServerSocket(8080)).getConnections();
    }
}
