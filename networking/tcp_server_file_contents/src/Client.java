import java.io.*;
import java.net.Socket;

public class Client {

    Socket socket;
    BufferedReader keyboardBufferedReader;
    BufferedReader contentBufferedReader;
    InputStream inputStream;
    OutputStream outputStream;
    PrintWriter printWriter;
    String fileName;
    String fileOutput;

    public Client(String host, int port) throws  IOException {
        this.socket = new Socket(host, port);
    }

    public void startServerCommunication() throws IOException{

        // reading the file name, using the input stream
        System.out.print("Enter the file name");
        keyboardBufferedReader = new BufferedReader(new InputStreamReader(System.in));
        fileName = "/home/martim/Documents/Programming/academiadecodigo/exercises/tcp_server_file_contents/resources/" + keyboardBufferedReader.readLine();

        // sending the file name to server. Uses PrintWriter
        outputStream = socket.getOutputStream( );
        printWriter = new PrintWriter(outputStream, true);
        printWriter.println(fileName);

        // receiving the contents from server.  Uses input stream
        inputStream = socket.getInputStream();
        contentBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        while((fileOutput = contentBufferedReader.readLine())  !=  null) { // reading line-by-line
            System.out.println(fileOutput);
        }

    }

    public void endServerCommunication() throws  IOException {

        printWriter.close();
        contentBufferedReader.close();
        keyboardBufferedReader.close();

    }

    public static void main(String args[ ] ) throws IOException {

        Client client = new Client("127.0.0.1", 5000);
        client.startServerCommunication();
        client.endServerCommunication();
    }
}
