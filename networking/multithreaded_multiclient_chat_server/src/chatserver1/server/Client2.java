package chatserver1.server;

import chatserver1.client.ChatClient;

import java.io.IOException;
import java.net.Socket;

public class Client2 {
    public static void main(String[] args) {
        try {
            new ChatClient(new Socket("127.0.0.1", 8080)).handleClientConnection();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
