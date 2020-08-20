package com.martimlima.javaprojects.io.file_copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

    private FileInputStream in = null;
    private FileOutputStream out = null;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: FileCopy <source_file> <destination_file>");
            System.exit(1);
        }

        FileCopy fileCopy = new FileCopy();
        fileCopy.start(args[0], args[1]);
    }

    private void start(String org, String dst) {

        byte[] buffer = new byte[1024];

        try {
            in = new FileInputStream(org);
            out = new FileOutputStream(dst);

            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            cleanUp();
            System.exit(1);
        } finally {
            cleanUp();
        }
    }

    private void cleanUp() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
