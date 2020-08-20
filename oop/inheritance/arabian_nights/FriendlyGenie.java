package com.martimlima.javaprojects.oop.inheritance.arabian_nights;

public class FriendlyGenie extends Genie {

    public FriendlyGenie(int numberOfWishes) {
        super(numberOfWishes);
    }

    @Override
    public void grantWish(String wish) {


        System.out.println("Tell me your wish and it shall be granted puny mortal");

        if(canGrantWish()) {

            System.out.println("You wished for a " + wish + ". I shall now grant you your wish\n");
            incrementWishesGranted();

        } else {

            System.out.println("You're out of wishes, can't grant you more\n");
        }
        decrementMaximumWishes();
    }


    @Override
    public String toString() {
        return "Friendly genie has granted you " + getMaximumNumberOfWishes() + " wishes so far and still has "

                + remainingWishes() + " to grant, so please be wise with what you wish for.";
    }

}
