package org.academiadecodigo.porto;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {

    private static void getContentFromUrl(String url) {

        String line;
        URL url2;
        InputStream urlStream;
        DataInputStream html;

        try {

            url2 = new URL(url);
            urlStream = url2.openStream();
            html = new DataInputStream(urlStream);

            line = new String(html.readAllBytes());

            System.out.println("***** URL content *****" + "\n");
            while ((line != null)) {
                System.out.println(line);
            }

        } catch (Exception ex) {
            System.out.println("ex: " + ex);
        }
    }

    private static String getContentFromHttpUrl(URL url) throws IOException {

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            try {
                InputStream in = urlConnection.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean urlInput = scanner.hasNext();

                String response = null;
                if (urlInput) {
                    response = scanner.next();
                }
                scanner.close();
                return response;

            } catch(IOException ex) {
                ex.printStackTrace();

            } finally {

                urlConnection.disconnect();
            }

           return null;
       }

    private static void getContentFromHttpUrl2(URL url) {

           try {

               String inputLine;

               try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {

                   System.out.println("***** URL content *****" + "\n");

                   while ((inputLine = in.readLine()) != null) {
                       System.out.println(inputLine);
                   }
               }

           }catch(IOException ex) {
               ex.printStackTrace();
           }
       }

    public static void main(String[] args) {

        try {

            getContentFromUrl("https://www.sapo.pt");

            String content = getContentFromHttpUrl(new URL("http", "www.google.com", ""));
            System.out.println("***** URL content *****" + "\n");
            System.out.println(content);

            System.out.println();

            getContentFromHttpUrl2(new URL("http", "www.google.com", ""));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
