package org.academiadecodigo.porto.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server2 implements Runnable{

    private static final int PORT = 8080;

    private boolean connected = false;
    private ServerSocket serverSocket;
    private Thread runningThread;
    private ExecutorService threadPool = Executors.newFixedThreadPool(30);


    @Override
    public void run() {

        synchronized (this) {

            this.runningThread = Thread.currentThread();
        }

        openServerSocket();

        while(true) {

            Socket clientSocket = null;

            try {

                clientSocket = this.serverSocket.accept();

            } catch (IOException ex) {

                if(isConnected()) {

                    System.out.println("Server Stopped");
                    break;
                }

                throw new RuntimeException("Error accepting Client Connection", ex);
            }

            this.threadPool.execute(new HttpRequestHandler(clientSocket));
            //new Thread(new HttpRequestHandler(clientSocket)).start();
        }

        this.threadPool.shutdown();
        System.out.println("Server Stopped");

    }

    private synchronized boolean isConnected() {
        return connected;
    }

    public synchronized void stop(){

        this.connected = true;

        try {
            this.serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {

        try {

            this.serverSocket = new ServerSocket(PORT);

        } catch (IOException e) {

            throw new RuntimeException("Cannot open port 8080", e);
        }
    }


    public static void main(String[] args) {

        Server2 server = new Server2();
        new Thread(server).start();
    }

}
