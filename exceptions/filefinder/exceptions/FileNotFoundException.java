package com.martimlima.javaprojects.exceptions.filefinder.exceptions;

public class FileNotFoundException extends FileException {

    public FileNotFoundException() {
        super("File not found!");
    }
}
