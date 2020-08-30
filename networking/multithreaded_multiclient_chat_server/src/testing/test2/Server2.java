package testing.test2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Vector;

public class Server2 {

    // Vector to store active clients
    static Vector<ClientHandler> clientConnections = new Vector<>();

    // counter for clients
    private static int i = 0;

    public static void main(String[] args) throws IOException
    {
        // chatserver1.server is listening on port 1234
        ServerSocket serverSocket = new ServerSocket(1234);

        Socket socket;

        // running infinite loop for getting
        // chatserver1.client request
        while (true) {
            // Accept the incoming request
            socket = serverSocket.accept();

            System.out.println("New chatserver1.client request received : " + socket);

            // obtain input and output streams
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            System.out.println("Creating a new handler for this chatserver1.client...");

            // Create a new handler object for handling this request.
            ClientHandler clientHandler = new ClientHandler(socket, "chatserver1/client " + i, inputStream, outputStream);

            // Create a new Thread with this object.
            Thread clientThread = new Thread(clientHandler);

            System.out.println("Adding this chatserver1.client to active chatserver1.client list");

            // add this chatserver1.client to active clients list
            clientConnections.add(clientHandler);

            // start the thread.
            clientThread.start();

            // increment i for new chatserver1.client.
            // i is used for naming only, and can be replaced
            // by any naming scheme
            i++;

        }
    }
}
