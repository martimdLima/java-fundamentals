package com.martimlima.javaprojects.oop.inheritance.arabian_nights;

public class GrumpyGenie extends Genie {

    public GrumpyGenie(int numberOfWishes) {
        super(numberOfWishes);
    }

    @Override
    public void grantWish(String wish) {

        System.out.println("Tell me your wish and it shall be granted puny mortal");

        if(getNumberOfWishesGranted() >= 1) {
            System.out.println("Not moving from over here mofo\n");
            return;
        }
        System.out.println("I will grant you this " + wish + " and only this wish\n");
        incrementWishesGranted();
    }


    @Override
    public String toString() {
        return "Grumpy genie has granted you " + getMaximumNumberOfWishes() + " wishes so far and still has "

                + remainingWishes() + " to grant, so please be wise with what you wish for.";
    }
}
