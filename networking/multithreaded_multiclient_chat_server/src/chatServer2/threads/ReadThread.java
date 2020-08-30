package chatServer2.threads;

import chatServer2.client.ChatClient1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread extends Thread {

    private BufferedReader reader;
    private Socket socket;
    private ChatClient1 client;

    public ReadThread(Socket socket, ChatClient1 client) {
        this.socket = socket;
        this.client = client;

        try {

            InputStream input;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {

        while (true) {

            try {
                String response = reader.readLine();
                System.out.println("\n" + response);

                // prints the username after displaying the chatserver1.server's message
                if (client.getUserName() != null) {
                    System.out.print("[" + client.getUserName() + "]: ");
                }
            } catch (IOException ex) {
                System.out.println("Error reading from chatserver1.server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}