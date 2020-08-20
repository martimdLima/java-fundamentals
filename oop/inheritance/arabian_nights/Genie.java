package com.martimlima.javaprojects.oop.inheritance.arabian_nights;

public class Genie {

    private int maximumNumberOfWishes;
    private int numberOfWishesGranted;

    public Genie(int numberOfWishes) {
        this.maximumNumberOfWishes = numberOfWishes;
        this.numberOfWishesGranted = 0;
    }


    public void grantWish(String wish) {

        System.out.println("Tell me your wish and it shall be granted puny mortal\n");

        if(!canGrantWish()) {
            System.out.println("You're out of wishes, can't grant you more\n");
        }

        System.out.println("You wished for a " + wish + ". I shall now grant you your wish\n");
        incrementWishesGranted();
    }


    public boolean canGrantWish() {
        return numberOfWishesGranted < maximumNumberOfWishes;
    }


    public int getMaximumNumberOfWishes() {
        return this.maximumNumberOfWishes;
    }


    public int remainingWishes() {
        return maximumNumberOfWishes - numberOfWishesGranted;
    }


    public int getNumberOfWishesGranted() {
        return numberOfWishesGranted;
    }


    public void decrementMaximumWishes() {
        this.maximumNumberOfWishes--;
    }


    public void incrementWishesGranted() {
        this.numberOfWishesGranted++;
    }

}
