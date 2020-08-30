package chatServer2.server;

import chatServer2.threads.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer1 {

    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<ClientThread> clientThreads = new HashSet<>();

    public ChatServer1(int port) {
        this.port = port;
    }

    public void execute() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Chat Server is listening on port " + port);

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("New user connected");

                ClientThread newUser = new ClientThread(socket, this);
                clientThreads.add(newUser);
                newUser.start();

            }

        } catch (IOException ex) {
            System.out.println("Error in the chatserver1.server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void broadcast(String message, ClientThread excludeUser) {

        for (ClientThread client : clientThreads) {

            if (client != excludeUser) {
                client.sendMessage(message);
            }
        }
    }

    public void addUserName(String userName) {
        userNames.add(userName);
    }

    public void removeUser(String userName, ClientThread client) {

        boolean removed = userNames.remove(userName);
        if (removed) {

            clientThreads.remove(client);
            System.out.println("The user " + userName + " quitted");
        }
    }

    public Set<String> getUserNames() {
        return this.userNames;
    }


    public boolean hasUsers() {
        return !this.userNames.isEmpty();
    }


    public static void main(String[] args) {

        int port = Integer.parseInt("8080");

        ChatServer1 server = new ChatServer1(port);
        server.execute();
    }

}