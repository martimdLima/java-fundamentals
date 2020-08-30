import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket serverSocket;
    Socket socket;
    BufferedReader fileBufferedReader;
    BufferedReader contentBufferedReader;
    InputStream inputStream;
    OutputStream outputStream;
    PrintWriter printWriter;
    String fileName;
    String fileOutput;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        System.out.println("Server ready, waiting for connection");
        this.socket = serverSocket.accept();
        System.out.println("Connection is successful and waiting for chatting");
    }

    public void startClientConnection() throws IOException {

        // reading the file name from client
        inputStream = socket.getInputStream( );
        fileBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        fileName = fileBufferedReader.readLine( );

        // reading file contents
        contentBufferedReader = new BufferedReader(new FileReader(fileName) );

        // keeping output stream ready to send the contents
        outputStream = socket.getOutputStream( );
        printWriter = new PrintWriter(outputStream, true);

        // reading line-by-line from file
        while((fileOutput = contentBufferedReader.readLine()) !=  null) {

            printWriter.println(fileOutput); // sending each line to client
        }

        fileBufferedReader.close();
        contentBufferedReader.close();
        printWriter.close();

    }

    public void endClientConnection() throws IOException {

        serverSocket.close();
        socket.close();
    }

    public static void main(String args[]) throws Exception {

        Server server = new Server(5000);

        server.startClientConnection();

        server.endClientConnection();
    }

}
