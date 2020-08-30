package chatServer2.threads;

import chatServer2.server.ChatServer1;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket socket;
    private ChatServer1 server;
    private PrintWriter writer;

    public ClientThread(Socket socket, ChatServer1 server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {

        try {

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            printUsers();

            String userName = reader.readLine();
            server.addUserName(userName);

            String serverMessage = "New user connected: " + userName;
            server.broadcast(serverMessage, this);

            String clientMessage;

            do {
                clientMessage = reader.readLine();

                if(clientMessage.equals("list")) {
                    printUsers();
                }

                serverMessage = "[" + userName + "]: " + clientMessage;
                server.broadcast(serverMessage, this);

            } while (!clientMessage.equals("bye"));

            server.removeUser(userName, this);
            socket.close();

            serverMessage = userName + " has quitted.";
            server.broadcast(serverMessage, this);

        } catch (IOException ex) {
            System.out.println("Error in CThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Sends a list of online users to the newly connected user.
     */

    public void printUsers() {

        if (server.hasUsers()) {
        writer.println("Connected users: " + server.getUserNames());
    } else {
        writer.println("No other users connected");
    }
}

    /**
     * Sends a message to the chatserver1.client.
     */
    public void sendMessage(String message) {
        writer.println(message);
    }
}