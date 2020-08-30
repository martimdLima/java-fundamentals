package chatServer2.client;

import chatServer2.threads.ReadThread;
import chatServer2.threads.WriteThread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient1 {

    private String hostname;
    private int port;
    private String userName;

    public ChatClient1(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute() {

        try {
            Socket socket = new Socket(hostname, port);

            System.out.println("Connected to the chat chatserver1.server");

            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }

    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }


    public static void main(String[] args) {

        ChatClient1 client = null;

        try {

            client = new ChatClient1(InetAddress.getLocalHost().getHostName(), 8080);
            client.execute();
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}