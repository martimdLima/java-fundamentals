package com.martimlima.javaprojects.exceptions.filefinder.exceptions;

public class NotEnoughSpaceException extends FileException {

    public NotEnoughSpaceException() {
        super("Not enough space!");
    }
}