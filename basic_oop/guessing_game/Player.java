package com.martimlima.javaprojects.basic_oop.guessing_game;

public class Player {

    private int playerGuess;

    public Player() {
        this.playerGuess = playerGuess;
    }

    public int getPlayerGuess() {
        return playerGuess;
    }

    public int generateRandomGuess(int min, int max) {
        return Randomizer.getRandomDoubleBetweenRange(min, max);
    }
}
