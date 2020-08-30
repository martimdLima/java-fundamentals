package chatserver1.server;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ChatServerThread extends Thread {
    private Socket socket; // each instance is clientInput a different thread and has its own socket
    private ChatServer server;	// the main chatserver1.server instance
    private String clientName;	// chatserver1.client's clientName (first interaction with chatserver1.server)
    private Set<String> clientList;
    private BufferedReader clientInput;	// from chatserver1.client
    private PrintWriter serverOutput; // to chatserver1.client

    public ChatServerThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {

        try {

            // Communication channel
            clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            serverOutput = new PrintWriter(socket.getOutputStream(), true);
            clientList = new HashSet<>();

            clientName = clientInput.readLine();
            clientList.add(clientName);
            System.out.println(clientName + " has connected...");
            serverOutput.println("welcome "+ clientName);
            server.broadcast(this, clientName + " entered the room");

            // keep the channel open
            String line;

            while ((line = clientInput.readLine()) != null) {

                if(line.equals("/Exit")) {
                    break;
                }

                if(line.equals("/List")) {
                    printUsers();
                    //serverOutput.println(Arrays.toString(clientList.toArray()));
                }

                String msg = clientName + ":" + line;
                System.out.println(msg);
                server.broadcast(this, msg);
            }

            // when done one
            System.out.println(clientName + " disconnected");
            server.broadcast(this, clientName + " left the room");

            // close chatserver1.server operations
            server.removeClientConnection(this);
            serverOutput.close();
            clientInput.close();
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printUsers() {

        String users = "";

        for(String client : clientList) {
            users = client + ", ";
        }

        serverOutput.println("Connected users " + users);

    }

    public String getClientName() {
        return clientName;
    }

    // Sends a message to the chatserver1.client

    public void send(String msg) {
        serverOutput.println(msg);
    }
}

