package com.martimlima.javaprojects.oop.hotel_simulation;

public class Customer {

    private String name;
    private Hotel hotel;
    private int roomKey;

    public Customer(String name) {

        this.name = name;
        this.hotel = hotel;
        this.roomKey = -1;

    }


    // Getters

    public Hotel getHotel() {
        return hotel;
    }

    public int getRoomKey() {
        return roomKey;
    }


    // Setters

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setRoomKey(int roomKey) {
        this.roomKey = roomKey;
    }


    // Methods

    public void checkInHotel() {
        setHotel(hotel);
        hotel.CheckInCustomer(); // (assign key to the customer)

    }

    public void checkOutHotel() {
        hotel.CheckOutCustomer(); // (assign key back to -1 and vacate the room)

    }
}
