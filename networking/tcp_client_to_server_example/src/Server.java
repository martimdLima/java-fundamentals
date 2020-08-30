import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    /*
     * Server Class Properties
     * serverSocket - The serverSocket, responsible for handling the creation of the client socket. It takes one parameter(int port)
     * socket - the client socket is created when the serverSocket establishes a new connection
     * inputStream - starting point on the server program. The server uses input stream as it is receiving the message.
     * dataInputStream - As InputStream is an abstract class, it cannot be used directly. It is chained to a concrete class.
     */

    ServerSocket serverSocket;
    Socket socket;
    InputStream inputStream;
    DataInputStream dataInputStream;
    String userMessage;

    /*
     * Server class constructor
     * @param port
     * @throws IOException
     */
    public Server(int port) throws IOException {
        System.out.println("server is ready, waiting for a connection...");  //  message to know the server is running
        this.serverSocket = new ServerSocket(port);
        this.socket = serverSocket.accept(); // used by the server, to bind the connection on the desired port, requested by the client.
        System.out.println("Server has connected with the user");

    }

    // Starts the communication with the client

    public void startConnection() throws IOException {

        inputStream = socket.getInputStream();
        dataInputStream = new DataInputStream(inputStream);
        // reads the message string from the socket and returns. This message is printed at the console.
        userMessage = new String(dataInputStream.readAllBytes());
        System.out.println(userMessage);

        dataInputStream .close();
        inputStream.close();
    }

    // Closes the connection with the server

    public void closeConnection() throws IOException {

        socket.close();
        serverSocket.close();
    }

    public static void main(String args[]) throws Exception {

        Server server = new Server(5000);

        server.startConnection();
        server.closeConnection();
    }
}
