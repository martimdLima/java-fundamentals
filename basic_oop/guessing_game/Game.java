package com.martimlima.javaprojects.basic_oop.guessing_game;

public class Game {

    private int gameGuess;
    private int numberOfPlayers;
    private Player[] playersArray;

    public Game(int numberOfPlayers) {
        this.gameGuess = gameGuess;
        this.numberOfPlayers = numberOfPlayers;
        this.playersArray = playersArray;
    }

    public void setGameGuess(int min, int max) {
        this.gameGuess = Randomizer.generateRandomIntBetweenRange(min, max);
    }

    public Player[] generatePlayerArray(int min, int max) {

        Player[] players = new Player[this.numberOfPlayers];

        for(int j = 0; j < players.length; j++) {
            players[j] = new Player();
            players[j].generateRandomGuess(min, max);
        }

        return players;
    }

    public void Start(int min, int max) {

        Player[] players = generatePlayerArray(min, max);
        int[] answers = new int[10];
        setGameGuess(min, max);
        boolean win = false;
        int counter = 0;

        while (!win && counter < max) {

            for (int i = 0; i < players.length; i++) {

                int playerGuess = players[i].generateRandomGuess(min, max);
                answers[counter++] = playerGuess;
                
                if (playerGuess == this.gameGuess) {
                    System.out.println("You won the game - your number was: " + playerGuess);
                    win = true;
                    break;

                } else {
                    System.out.println("keep trying - your number was: " + playerGuess);
                }
            }

        }
        for(int number : answers) {
            System.out.print(number + ", ");
        }
    }
}
