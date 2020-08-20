package com.martimlima.javaprojects.exceptions.filefinder.exceptions;

public class NotEnoughPermissionsException extends FileException {

    public NotEnoughPermissionsException() {
        super("Not enough permissions!");
    }
}
