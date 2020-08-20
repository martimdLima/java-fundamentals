package com.martimlima.javaprojects.basic_oop.rock_paper_scissors_v2;

public class Player {

    private GameOptions playerChoice;

    public Player() {
        this.playerChoice = playerChoice;
    }

    public GameOptions GeneratePlayerChoice() {

        int playerChoice = Randomizer.generateRandomIntBetweenRange(1,4);

        switch(playerChoice) {
            case 1: return GameOptions.ROCK;
            case 2 : return GameOptions.PAPER;
            case 3 : return GameOptions.SCISSORS;
        }

        return GeneratePlayerChoice();
    }

    public GameOptions getPlayerChoice() {
        return playerChoice;
    }

    public void setPlayerChoice() {
        this.playerChoice = GeneratePlayerChoice();
    }
}
