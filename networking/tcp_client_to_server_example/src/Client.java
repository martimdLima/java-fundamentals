import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

 // Class Client - Able to send wishes as a string to the server. As it is sending, it uses output streams.

public class Client {

    /*
     * socket - responsible for the communications with the server, it takes two parameters (String host, int port)
     * outputStream - starting point of the actual communication. Here the socket gets linked to the streams.
     * dataOutputStream - takes the outputStream and passes it to the socket.
     * message - the message the client wants to send to the server
     */

    Socket socket;
    OutputStream outputStream;
    DataOutputStream dataOutputStream;
    String message;

    /*
     * Client class constructor
     * @param host - desired host
     * @param port - desired port
     * @param message - desired message to send to the server
     * @throws IOException
     */

    public Client(String host, int port, String message) throws IOException {
        this.socket = new Socket(host, port);

        this.message = message;
    }

    // Starts the communication with the server

    public void startConnection() throws IOException {

        // starting point of the whole communication. Here the socket get's linked to the streams;

        outputStream = socket.getOutputStream();

        // since OutputStream is an abstract Class it cannot be used directly, so it's chained to concrete class
        // DataOutputStream. writeBytes() method of DataOutputStream takes the string message and passes to the Socket.
        // Now the client socket sends to the other socket on the server.

        dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes(message);


        // When the transmission is over close the data streams.

        dataOutputStream.close();
        outputStream.close();
    }

    // closes the connection with the socket

    public void closeConnection() throws  IOException {

        // closes the socket and it releases the handles (links) connected to the system resources.

        socket.close();
    }

    // Point of entry, instantiates the client and starts the connection with the server

    public static void main(String args[]) throws Exception {

        Client client = new Client("127.0.0.1", 5000, "Hey there Server");

        client.startConnection();

        client.closeConnection();
    }
}
