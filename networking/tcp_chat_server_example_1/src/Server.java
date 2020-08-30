import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader keyboardInput;
    private BufferedReader inputReader;
    private InputStream inputStream;
    private OutputStream outputStream;
    private PrintWriter outputWriter;
    private String receivedMessage;
    private String sentMessage;

    public Server(int port) throws IOException {

        System.out.println("Binding to port " + port + ", please wait  ...");
        this.serverSocket = new ServerSocket(port);

        System.out.println("Server started: " + serverSocket);
        System.out.println("Waiting for a client ...");

        this.socket = serverSocket.accept();
        System.out.println("Client accepted: " + socket + "\n");

        this.receivedMessage = "";
        this.sentMessage = "";
    }

    public void startClientConnection() throws IOException {

        // reading from keyboard
        keyboardInput = new BufferedReader(new InputStreamReader(System.in));

        // sending to client
        outputStream = socket.getOutputStream();
        outputWriter = new PrintWriter(outputStream, true);

        // receiving from server ( receiveRead  object)
        inputStream = socket.getInputStream();
        inputReader = new BufferedReader(new InputStreamReader(inputStream));

        while(!receivedMessage.equals("stop")) {

            if((receivedMessage = inputReader.readLine()) != null) {
                System.out.println("User message: " + receivedMessage);
            }

            System.out.print("server message: ");
            sentMessage = keyboardInput.readLine();
            outputWriter.println(sentMessage);
            outputWriter.flush();
        }
        endConnection();
    }

    public void endConnection() throws IOException {

        serverSocket.close();
        socket.close();
        keyboardInput.close();
        inputStream.close();
        inputReader.close();
        outputStream.close();
        outputWriter.close();
    }

    public static void main(String[] args)  throws Exception {

        Server server = new Server(5000);

        server.startClientConnection();
        server.endConnection();
    }
}