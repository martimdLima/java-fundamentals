import java.io.*;
import java.net.Socket;


public class Client {
    
    private Socket socket;
    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private BufferedReader bufferedReader;
    private String userMessage;
    private String serverMessage;

    public Client(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
    }

    public void startConnection() throws IOException {

        dataInput = new DataInputStream(socket.getInputStream());
        dataOutput = new DataOutputStream(socket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Start the chitchat, type and press Enter key");

        userMessage = "";
        serverMessage = "";

        while (!userMessage.equals("stop")) {

            // user message
            userMessage = bufferedReader.readLine();
            dataOutput.writeUTF(userMessage);
            dataOutput.flush();

            // server message
            serverMessage = dataInput.readUTF();
            System.out.println("Server says: " + serverMessage);
        }

        endConnection();
    }

    public void endConnection() throws IOException {
        dataOutput.close();
        socket.close();
    }


    public static void main(String args[]) throws IOException {

        Client client = new Client("127.0.0.1", 5000);
        client.startConnection();
    }

}

