import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Server class - responsible for sending a message to client. As it is sending, it uses an output streams.

public class Server {

    ServerSocket serverSocket;
    Socket socket;
    OutputStream outputStream;
    BufferedWriter bufferedWriter;
    String message;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("server is ready, waiting for a connection...");  //  message to know the server is running
        this.socket = serverSocket.accept();
        System.out.println("Connection with the client established");
    }

    public void startServerCommunication() throws  IOException {

        outputStream = socket.getOutputStream();
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        message = "Thanks client for your calling on " + new java.util.Date();
        bufferedWriter.write(message);
        bufferedWriter.close();
        outputStream.close();
    }

    public void endServerCommunication() throws IOException {

        socket.close();
        serverSocket.close();
    }

    public static void main(String args[]) throws Exception {

        Server server = new Server(5000);
        server.startServerCommunication();
        server.endServerCommunication();
//        ServerSocket serverSocket = new ServerSocket(5000);
//        System.out.println("server is ready, waiting for a connection...");  //  message to know the server is running
//
//        Socket socket = serverSocket.accept();
//
//        OutputStream outputStream = socket.getOutputStream();
//        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
//        String serverMessage = "Thanks client for your calling on " + new java.util.Date();
//        bufferedWriter.write(serverMessage);
//
//        bufferedWriter.close();
//        outputStream.close();
//        socket.close();
//        serverSocket.close( );
    }
}
