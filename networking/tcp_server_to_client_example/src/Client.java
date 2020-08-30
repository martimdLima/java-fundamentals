import java.io.*;
import java.net.Socket;

// Client class - responsible for receiving the message from the server. As it is receiving, it uses input streams.
public class Client {

    Socket socket;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    String message;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
    }

    public void startServerCommunication() throws IOException {

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        message = bufferedReader.readLine();

        System.out.println(message);
    }

    public void endServerCommunication() throws IOException {

        socket.close();
    }

    public static void main(String args[]) throws IOException {

        Client client = new Client("127.0.0.1", 5000);

        client.startServerCommunication();

        client.endServerCommunication();
    }
}
