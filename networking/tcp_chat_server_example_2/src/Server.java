import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private BufferedReader bufferedReader;
    private String userMessage;
    private String serverMessage;

    public Server(int port) throws IOException {

        System.out.println("Binding to port " + port + ", please wait  ...");
        this.serverSocket = new ServerSocket(port);

        System.out.println("Server started: " + serverSocket);
        System.out.println("Waiting for a client ...");

        this.socket = serverSocket.accept();
        System.out.println("Client accepted: " + socket);
    }

    public void startConnection() throws IOException {

        dataInput = new DataInputStream(socket.getInputStream());
        dataOutput = new DataOutputStream(socket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        userMessage = "" ;
        serverMessage = "";

        while (!userMessage.equals("stop")) {

            // user message (output)
            userMessage = dataInput.readUTF();
            System.out.println("client says: " + userMessage);

            // server message (input)
            serverMessage = bufferedReader.readLine();
            dataOutput.writeUTF(serverMessage);
            dataOutput.flush();
        }

        endConnection();
    }

    public void endConnection() throws IOException {

        dataInput.close();
        socket.close();
        serverSocket.close();
    }

    public static void main(String args[]) throws IOException {

        Server server = new Server(5000);
        server.startConnection();
    }
}

