package testing.test1;

import java.net.*;
import java.io.*;

public class ChatCThread extends Thread {
    private Socket           socket   = null;
    private ChatC       client   = null;
    private DataInputStream inputStream = null;

    public ChatCThread(ChatC _client, Socket _socket) {
        super();
        client   = _client;
        socket   = _socket;
        open();
        start();
    }
    public void open()
    {  try
    {  inputStream = new DataInputStream(socket.getInputStream());
    }
    catch(IOException ioe)
    {  System.out.println("Error getting input stream: " + ioe);
        client.stop();
    }
    }
    public void close()
    {  try
    {  if (inputStream != null) inputStream.close();
    }
    catch(IOException ioe)
    {  System.out.println("Error closing input stream: " + ioe);
    }
    }
    public void run()
    {  while (true)
    {  try
    {  client.handle(inputStream.readUTF());
    }
    catch(IOException ioe)
    {  System.out.println("Listening error: " + ioe.getMessage());
        client.stop();
    }
    }
    }
}
