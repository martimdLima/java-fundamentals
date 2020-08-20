package com.martimlima.javaprojects.oop.inheritance.arabian_nights;

public class RecyclableDemon extends Genie {

    boolean recycled;

    public RecyclableDemon(int numberOfWishes) {
        super(numberOfWishes);
        this.recycled = false;
    }


    public boolean isRecycled() {
        return recycled;
    }


    public boolean demonCleanser() {
        return this.recycled = true;
    }


    @Override
    public void grantWish(String wish) {

        System.out.println("I am the great Recyclable demon, ask me anything and it shall be granted to you\n");

        if(!isRecycled()) {
            System.out.println("Recyclable demon has granted you a " + wish);
        } else {
            System.out.println("Demon has been recycled, long live belzebub");
        }
    }


    @Override
    public String toString() {
        return "Recyclable Demon has granted you " + getMaximumNumberOfWishes() + " wishes so far and still has "

                + remainingWishes() + " to grant, so please be wise with what you wish for.";
    }
}
