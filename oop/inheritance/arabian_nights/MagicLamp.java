package com.martimlima.javaprojects.oop.inheritance.arabian_nights;

public class MagicLamp {

    String name;
    private int capacity;
    private int rubs;
    private int numberOfGeniesOut;
    private int numberOfRecharges;

    public MagicLamp(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.rubs = 0;
        this.numberOfGeniesOut = 0;
        this.numberOfRecharges = -1;
    }


    public int getRubs() {
        return rubs;
    }


    private void incrementRubs() {
        this.rubs++;
    }


    private void incrementNumberOfGeniesOut() {
        this.numberOfGeniesOut++;
    }


    public Genie rubTheLamp() {
        int numberOfWishes = (int) (Math.random()*5);
        incrementRubs();
        incrementNumberOfGeniesOut();

        if (rubs >= capacity) {
            return new RecyclableDemon(numberOfWishes);
        }

        if (numberOfGeniesOut % 2 == 0) {
            return new FriendlyGenie(numberOfWishes);

        }

        return new GrumpyGenie(numberOfWishes);


/*      Other solution to the rubLamp() behavior

        if(!hasGenies) {
             return new RecyclableDemon;
         }

         return isEven() ? new FriendlyGenie(numberOfWishes) : new GrumpyGenie(numberOfWishes);*/
    }


    public void recycleLamp(Genie genie) { //Recyclable Demon demon

    /*  Method solution using a Recyclable demon as a parameter;

        if (!demon.isRecycled()) {
            demon.demonCleanser();
            rubs = 0;
            numberOfRecharges++;
        }

        System.out.println("The demon is already recycled so you can't use him again! Please input a new Demon");*/

        RecyclableDemon demon = null;

        // Cast Safety

        if(!(genie instanceof RecyclableDemon)) {

            System.out.println("The genie you are trying to recycle isn't a demon");
            return;
        }

        demon = (RecyclableDemon) genie;

        if(!demon.isRecycled()) {
            demon.demonCleanser();
            rubs = 0;
            numberOfRecharges++;
        }

        System.out.println("The demon you are trying to recycle is already recycled");
    }


    public boolean compareLamps(MagicLamp magicLamp2) {

        return this.capacity == magicLamp2.capacity
                && this.rubs == magicLamp2.rubs
                && this.numberOfRecharges == magicLamp2.numberOfRecharges;
    }

}
