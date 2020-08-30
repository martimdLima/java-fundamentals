package chatserver1.client;

import java.io.*;
import java.net.*;

public class ChatClientThread extends Thread {

    private Socket socket;			// the underlying socket for communication
    private ChatClient client;		// for which this is handling communication
    private BufferedReader serverInput;		// from chatserver1.server
    private PrintWriter clientOutput;		// to chatserver1.server

    public ChatClientThread(Socket socket, ChatClient client) throws IOException {

        this.socket = socket;
        this.client = client;
        serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        clientOutput = new PrintWriter(socket.getOutputStream(), true);
    }

    public void send(String msg) {

        //called when we have keyboard input
        this.clientOutput.println(msg);
    }

    public void run() {

        // Get lines from chatserver1.server; print to console

        try {

            String line;

            while ((line = serverInput.readLine()) != null) {
                System.out.println(line);
            }
        }

        catch (IOException e) {
            e.printStackTrace();

        } finally {

            client.disconnect();
            System.out.println("chatserver1.server hung up");
        }

        // Clean up
        try {

            clientOutput.close();
            serverInput.close();
            socket.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
