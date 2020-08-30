package testing.test3;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/*
 * The chat chatserver1.client thread. This chatserver1.client thread opens the input and the output
 * streams for a particular chatserver1.client, ask the chatserver1.client's name, informs all the
 * clients connected to the chatserver1.server about the fact that a new chatserver1.client has joined
 * the chat room, and as long as it receive data, echos that data back to all
 * other clients. When a chatserver1.client leaves the chat room this thread informs also
 * all the clients about that and terminates.
 */
class ClientThread extends Thread {

    private DataInputStream is = null;
    private PrintStream os = null;
    private Socket clientSocket = null;
    private final ClientThread[] threads;
    private int maxClientsCount;

    public ClientThread(Socket clientSocket, ClientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    public void run() {
        int maxClientsCount = this.maxClientsCount;
        ClientThread[] threads = this.threads;

        try {
            /*
             * Create input and output streams for this chatserver1.client.
             */
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
            os.println("Enter your name.");
            String name = is.readLine().trim();
            os.println("Hello " + name
                    + " to our chat room.\nTo leave enter /quit in a new line");
            for (int i = 0; i < maxClientsCount; i++) {
                if (threads[i] != null && threads[i] != this) {
                    threads[i].os.println("*** A new user " + name
                            + " entered the chat room !!! ***");
                }
            }
            while (true) {
                String line = is.readLine();
                if (line.startsWith("/quit")) {
                    break;
                }
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null) {
                        threads[i].os.println("<" + name + "&gr; " + line);
                    }
                }
            }
            for (int i = 0; i < maxClientsCount; i++) {
                if (threads[i] != null && threads[i] != this) {
                    threads[i].os.println("*** The user " + name
                            + " is leaving the chat room !!! ***");
                }
            }
            os.println("*** Bye " + name + " ***");

            /*
             * Clean up. Set the current thread variable to null so that a new chatserver1.client
             * could be accepted by the chatserver1.server.
             */
            for (int i = 0; i < maxClientsCount; i++) {
                if (threads[i] == this) {
                    threads[i] = null;
                }
            }

            /*
             * Close the output stream, close the input stream, close the socket.
             */
            is.close();
            os.close();
            clientSocket.close();
        } catch (IOException e) {
        }
    }
}


