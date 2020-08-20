package com.martimlima.javaprojects.networking.udp_echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDPServer {

    private static final String CHARSET = "UTF8";
    private int port;

    public static void main(String args[]) {

        try {
            // create a UDPServer object
            UDPServer udpServer = new UDPServer();
            // gets the port from the console
            udpServer.getUserInput();
            // start the udp server
            udpServer.start();
        } catch (NumberFormatException ex) {
            System.err.println("Error: Invalid port!");
        } catch (SocketException ex) {
            System.err.println("Error: Could not accept incoming packets: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error: Network failure: " + ex.getMessage());
        }
    }

    // Wait for a client udp packet, process it and send it' content back to the sender
    private void start() throws IOException {

        DatagramSocket serverSocket = new DatagramSocket(port);
        byte[] receiveData = new byte[1024];

        while (serverSocket.isBound()) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            System.out.println("Waiting for datagram packet");

            serverSocket.receive(receivePacket);
            String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), CHARSET);
            InetAddress address = receivePacket.getAddress();

            int port = receivePacket.getPort();
            System.out.println("From: " + address.getHostName() + ":" + port);
            System.out.println("Message: " + message);
            String upperCase = message.toUpperCase();

            byte[] sendData = upperCase.getBytes(CHARSET);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            serverSocket.send(sendPacket);
        }
    }

    private void getUserInput() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Port? ");
        port = Integer.parseInt(in.readLine());
        in.close();
    }
}
