package testing.test2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;


class ClientHandler implements Runnable {

    Scanner scanner = new Scanner(System.in);
    private String name;
    final DataInputStream inputStream;
    final DataOutputStream outputStream;
    Socket socket;
    boolean loggedIn;

    // constructor
    public ClientHandler(Socket socket, String name, DataInputStream inputStream, DataOutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.name = name;
        this.socket = socket;
        this.loggedIn =true;
    }

    @Override
    public void run() {

        String received;
        while (true) {

            try {
                // receive the string
                received = inputStream.readUTF();

                System.out.println(received);

                if(received.equals("logout")){
                    this.loggedIn =false;
                    this.socket.close();
                    break;
                }

                // break the string into message and recipient part
                StringTokenizer st = new StringTokenizer(received, "#");

                String recipient = st.nextToken();
                String MsgToSend = st.nextToken();


                // search for the recipient in the connected devices list.
                // clientConnections is the vector storing chatserver1.client of active users
                for (ClientHandler clientConnection : Server2.clientConnections)
                {
                    // if the recipient is found, write on its
                    // output stream
                    if (clientConnection.name.equals(recipient) && clientConnection.loggedIn)
                    {
                        clientConnection.outputStream.writeUTF(this.name+" : "+MsgToSend);
                        break;
                    }
                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        try
        {
            // closing resources
            this.inputStream.close();
            this.outputStream.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
