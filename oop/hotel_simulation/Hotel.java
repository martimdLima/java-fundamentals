package com.martimlima.javaprojects.oop.hotel_simulation;

public class Hotel {

    private String name;
    private String location;
    private Room[] roomList;
    private Customer customer;

    public Hotel(String name, String location, int numberOfRooms) {

        this.name = name;
        this.location = location;
        generateRoomList();

    }


    // Getters

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Room[] getRoomList() {
        return roomList;
    }



    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    // Methods

    private void generateRoomList() {


        for(int i = 0; i < roomList.length; i++) {

            roomList[i] = new Room();
        }
    }

    public boolean checkRoomAvailability(Room[] roomList) {

        boolean isAvailable = false;

        for (int i = 0; i < roomList.length; i++) {

            if (roomList[i].isAvailable()) {
                isAvailable = true;
            }
        }
        return isAvailable;
    }

    public void CheckInCustomer() {

        // if a room is available, return true and the number of the room

        int roomKey;
        customer.checkInHotel();

        if(checkRoomAvailability(roomList)) {
            //customer.setRoomKey(roomKey);
            roomList[customer.getRoomKey()].occupyRoom();
        }


    }

    public void CheckOutCustomer() {

        customer.checkOutHotel();

        customer.setRoomKey(-1);
        roomList[customer.getRoomKey()].vacateRoom();

    }

}
