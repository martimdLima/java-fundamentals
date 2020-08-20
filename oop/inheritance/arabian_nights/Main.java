package com.martimlima.javaprojects.oop.inheritance.arabian_nights;

public class Main {

    public static void main(String[] args) {


         MagicLamp lamp = new MagicLamp("magicLamp",4);
         MagicLamp lamp2 = new MagicLamp("MagicLamp2", 4);

         Genie geniesInTheLamp[] = { lamp.rubTheLamp(), lamp.rubTheLamp(), lamp.rubTheLamp(), lamp.rubTheLamp(), lamp.rubTheLamp() };
         RecyclableDemon demon = null;


         System.out.println("\n//////////////////////////////////////////\n");


        for (Genie genie : geniesInTheLamp) {
            genie.grantWish("batata");

        }

        for(Genie genie : geniesInTheLamp) {

            if(genie instanceof RecyclableDemon) {
                demon = (RecyclableDemon) genie;
                break;
            }
        }

        System.out.println();

        lamp.recycleLamp(demon);
        lamp.recycleLamp(demon);
        lamp.rubTheLamp();


        System.out.println("\n//////////////////////////////////////////\n");


        System.out.println(lamp.compareLamps(lamp2));


        System.out.println("\n//////////////////////////////////////////\n");

    }
}
