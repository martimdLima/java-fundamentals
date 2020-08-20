package com.martimlima.javaprojects.oop.hotel_simulation;

public class Room {

    private int numberOfRoom;
    private boolean occupied;
    private Customer customer;

    public Room() {

        this.numberOfRoom = -1;
        this.occupied = false;

    }


    // Getters

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public boolean isAvailable() {
        return occupied;
    }

    // Setters


    // Methods

    public void changeRoomAvailability() {

        if(occupied) {
            this.occupied = false;

        } else {
            this.occupied = true;
        }
    }

    public void occupyRoom() {

    }

    public void vacateRoom() {

    }
}
