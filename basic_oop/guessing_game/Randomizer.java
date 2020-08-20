package com.martimlima.javaprojects.basic_oop.guessing_game;

import java.util.Random;

public class Randomizer {


    public static int getRandomDoubleBetweenRange(int min, int max){
        int x = (int) ((Math.random()*((max-min)+1))+min);
        return x;
    }

    public static double getRandomIntegerBetweenRange(double min, double max){
        double randomNumber = (Math.random()*((max-min)+1))+min;
        return randomNumber;
    }

    public static int generateRandomIntBetweenRange(int min, int max) {
        Random randomNumber = new Random();
        return randomNumber.nextInt((max - min) + 1) + min;
    }
}
