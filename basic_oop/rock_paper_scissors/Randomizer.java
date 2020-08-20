package com.martimlima.javaprojects.basic_oop.rock_paper_scissors;

import java.util.Random;

public class Randomizer {


    public static double getRandomDoubleBetweenRange(double min, double max){
        double x = (Math.random()*((max-min)+1))+min;
        return x;
    }

    public static double getRandomIntegerBetweenRange(int min, int max){
        int randomNumber = (int) ((Math.random()*((max-min)+1))+min);
        return randomNumber;
    }

    public static int generateRandomIntBetweenRange(int min, int max) {
        Random randomNumber = new Random();
        return randomNumber.nextInt((max - min) + 1) + min;
    }
}

