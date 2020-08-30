import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader keyboardInput;
    private BufferedReader inputReader;
    private InputStream inputStream;
    private OutputStream outputStream;
    private PrintWriter printWriter;
    private String receivedMessage;
    private String sentMessage;
    private boolean done;

    public Client(String host, int port) throws IOException {

        this.socket = new Socket(host, port);

        this.receivedMessage = "";
        this.sentMessage = "";
    }

    public void startServerConnection() throws IOException {

        // reading from keyboard input
        keyboardInput = new BufferedReader(new InputStreamReader(System.in));

        // sending input to client
        outputStream = socket.getOutputStream();
        printWriter = new PrintWriter(outputStream, true);

        // receiving input from server
        inputStream = socket.getInputStream();
        inputReader = new BufferedReader(new InputStreamReader(inputStream));

        System.out.println("Start the chat, type and press Enter key" + "\n");

        while(!sentMessage.equals("stop")) {

            // reading data from user keyboard input
            System.out.print("client says: ");
            sentMessage = keyboardInput.readLine();

            // sending the input to the server
            printWriter.println(sentMessage);

            // flushing the data from the buffer
            printWriter.flush();

            //receiving data from server keyboard input
            if((receivedMessage = inputReader.readLine()) != null) {
                // displaying the server message
                System.out.println("Server message: " + receivedMessage);
            }
        }

    endConnection();
    }

    public void endConnection() throws IOException {

        socket.close();
        keyboardInput.close();
        inputStream.close();
        inputReader.close();
        outputStream.close();
        printWriter.close();
    }

    public static void main(String[] args) throws Exception {

        Client client = new Client("127.0.0.1", 5000);

        client.startServerConnection();
        client.endConnection();
    }

}
