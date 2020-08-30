package org.academiadecodigo.porto.server;

import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

public class HttpRequestHandler implements Runnable {

    private static final File WEB_ROOT = new File(".");
    private static final String DEFAULT_FILE = "www/index.html";
    private static final String FILE_NOT_FOUND = "www/404.html";
    private static final String METHOD_NOT_SUPPORTED = "www/not_supported.html";
    private static final boolean connectionAccepted = true;

    private BufferedReader input = null;
    private PrintWriter output = null;
    private BufferedOutputStream dataOutput = null;
    private StringTokenizer stringParser;

    private Socket clientSocket;

    public HttpRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;

    }

    @Override
    public void run() {

        // manages the client connection

        String requestedFile = "";

        try {

//            // reads characters from the client via input stream on the socket
//            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//            // gets the character output stream to client (for headers)
//            output = new PrintWriter(clientSocket.getOutputStream());
//
//            // gets the binary output stream to client (for requested data)
//            dataOutput = new BufferedOutputStream(clientSocket.getOutputStream());

            init();

            // gets the first line of the request from the client
            String clientRequest = input.readLine();

            if (input.readLine() == null) {
                return;
            }

            // parse the request with a string tokenizer
            stringParser = new StringTokenizer(clientRequest);


            String method = stringParser.nextToken().toUpperCase();

            // gets the file requested
            String fileRequested = stringParser.nextToken().toLowerCase();

            //init();

            // checks for the supported methods GET and HEAD
            if (!method.equals("GET") && !method.equals("HEAD")) {
                // if it doesn't match the supported methods, returns the requested supported file to the client
                requestNotFound(method, fileRequested);
            }
            // if it matches the supported methods, returns the requested not supported file to the client
            requestFound(fileRequested, method, output, dataOutput);

        } catch (FileNotFoundException ex) {

            try {
                fileNotFound(output, dataOutput, requestedFile);

            } catch (IOException ioe) {
                System.err.println("Error with file not found exception : " + ioe.getMessage());
            }

        } catch (IOException ioe) {
            System.err.println("Server error : " + ioe);

        } finally {

            try {
                // after the file is served to the client, close the connections
                input.close();
                output.close();
                dataOutput.close();
                clientSocket.close();

            } catch (Exception ex) {
                System.out.println("Error closing the stream: " + ex.getMessage());
            }

            // if we get to this point, without throwing any exception, a message is display signaling it
            if (connectionAccepted) {

                System.out.println("Connection with the client is now closed");
            }
        }
    }

    private void init() {

        try {

            // reads characters from the client via input stream on the socket
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // gets the character output stream to client (for headers)
            output = new PrintWriter(clientSocket.getOutputStream());

            // gets the binary output stream to client (for requested data)
            dataOutput = new BufferedOutputStream(clientSocket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void fileRequestParser() {
//
//        if (input.readLine() == null) {
//            return;
//        }
//
//        // parse the request with a string tokenizer
//        stringParser = new StringTokenizer(clientRequest);
//
//
//        String method = stringParser.nextToken().toUpperCase();
//
//        // gets the file requested
//        String fileRequested = stringParser.nextToken().toLowerCase();
//    }

    private void requestNotFound(String method, String fileRequested) {

        String contentType;

        int fileLength;

        PrintWriter output = null;
        OutputStream dataOutput = null;
        try {
            // if the connection is established, prints 501 followed by the corresponding file type
            if (connectionAccepted) {

                System.out.println("501: " + method);
            }

            // returns the not supported file to the client
            File file = new File(WEB_ROOT, METHOD_NOT_SUPPORTED);
            fileLength = (int) file.length();
            contentType = "text/html";
            byte[] fileData = readFileData(file, fileLength);   // reads content to return to client

            // sends HTTP Headers with data to client
            output.println("HTTP/1.1 501 Not Implemented");
            output.println("Server2: Java Http Server2 from mdLima : 1.0");
            output.println("Date: " + new Date());
            output.println("Content-type: " + contentType);
            output.println("Content-length: " + fileLength);
            output.println();   // blank line between headers and content
            output.flush();  // flush character output stream buffer

            // writes the data output to the file to the client and flushes the data output stream buffer
            dataOutput.write(fileData, 0, fileLength);
            dataOutput.flush();

        } catch (FileNotFoundException ex) {

            try {
                // if the file is not found in the server, deliver a file with the corresponding data to the client
                fileNotFound(output, dataOutput, fileRequested);

            } catch (IOException exc) {

                System.out.println("File not found exception: " + exc.getMessage());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void requestFound(String fileRequested, String method, PrintWriter output, BufferedOutputStream dataOutput) {

        String content;
        int fileLength;

        try {

            // GET or HEAD method
            if (fileRequested.endsWith("/")) {

                fileRequested += DEFAULT_FILE;
            }

            File file = new File(WEB_ROOT, fileRequested);
            fileLength = (int) file.length();
            content = getMimeType(fileRequested);

            // if the method equals GET, returns the content
            if (method.equals("GET")) {

                // reads the data from the file
                byte[] fileData = readFileData(file, fileLength);

                // sends HTTP Headers
                output.println("HTTP/1.1 200 OK");
                output.println("Server2: Java HTTP Server2 from mdLima : 1.0");
                output.println("Date: " + new Date());
                output.println("Content-type: " + content);
                output.println("Content-length: " + fileLength);
                output.println(); // blank line between headers and content
                output.flush(); // flushes the character output stream buffer

                // writes the data output to the file to the client and flushes the data output stream buffer
                dataOutput.write(fileData, 0, fileLength);
                dataOutput.flush();
            }

            if (connectionAccepted) {

                System.out.println("File: " + fileRequested + ", File type: " + content + " returned");
            }

        } catch (FileNotFoundException ex) {

            try {

                fileNotFound(output, dataOutput, fileRequested);

            } catch (IOException exc) {

                System.out.println("File not found exception: " + exc.getMessage());
            }

        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }

    private byte[] readFileData(File file, int fileLength) throws IOException {

        FileInputStream fileInput = null;

        byte[] fileData = new byte[fileLength];

        try {

            fileInput = new FileInputStream(file);
            fileInput.read(fileData);

        } finally {

            if (fileInput != null) {

                fileInput.close();
            }

        }

        return fileData;
    }

    private String getMimeType(String fileRequested) {

        // gets the fileRequested mime type
        String fileMimeType = fileRequested.substring(fileRequested.lastIndexOf(".")).replace(".", "").toUpperCase();

        // gets the corresponding mime type from the enum
        MimeType mimeType = MimeType.valueOf(fileMimeType);

        // gets the corresponding file type to return to the client
        switch (mimeType) {

            case HTM: case HTML:
                return "text/html";

            case CSH:
                return "application/x-csh";

            case CSS:
                return "text/css";

            case JS:
                return "application/javascript";

            case JSON:
                return "application/json";

            case TXT:
            case JAVA:
                return "text/plain";

            case PNG:
                return "image/png";

            case GIF:
                return "image/gif";

            case CLASS:
                return "application/octet-stream";

            case JPG:
                return "image/jpg";

            case JPEG:
                return "image/jpeg";

            case PDF:
                return "application/pdf";

            case ICO:
                return "image/ico";

            case WOFF2:
                return "font/woff2";

            case MP4:
                return "video/mp4";
            case WEBM:
                return "video/webm";

            default:
                return "text/plain";
        }
    }

    private void fileNotFound(PrintWriter output, OutputStream dataOutput, String requestedFile) throws IOException {

        File file = new File(WEB_ROOT, FILE_NOT_FOUND);
        int fileLength = (int) file.length();
        String contentType = "text.html";

        byte[] fileData = readFileData(file, fileLength);

        // sends HTTP Headers with data to client
        output.println("HTTP/1.1 501 Not Implemented");
        output.println("Server2: Java Http Server2 from mdLima : 1.0");
        output.println("Date: " + new Date());
        output.println("Content-type: " + contentType);
        output.println("Content-length: " + fileLength);
        output.println();
        output.close();

        dataOutput.write(fileData, 0, fileLength);
        dataOutput.flush();

        if (connectionAccepted) {

            System.out.println("File " + requestedFile + " not found");
        }

    }
}
