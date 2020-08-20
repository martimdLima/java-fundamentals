package com.martimlima.javaprojects.basic_oop.rock_paper_scissors;

public class GameEngine {

    private int computerScore;
    private int playerScore;
    private int numberOfPlays;
    private GameOptions computerChoice;
    private Player [] players;

    public GameEngine() {
        this.numberOfPlays = numberOfPlays;
        this.computerChoice = computerChoice;
        this.playerScore = playerScore;
        this.numberOfPlays = numberOfPlays;

    }


    private Player [] generatePlayerArray(int numberOfPlayers)  {

        Player [] playerArray = new Player[numberOfPlayers];

        for(int i = 0; i < numberOfPlayers; i++) {
            playerArray[i] = new Player();
        }

        return playerArray;
    }



    public GameOptions generatePlayerChoice() {

        int playerChoice = Randomizer.generateRandomIntBetweenRange(0,GameOptions.values().length-1);
        return GameOptions.values()[playerChoice];
    }


    private int compare(GameOptions player, GameOptions computer) {

        if (player.equals(computer)) {
            return 0;
        }

        if(player == GameOptions.ROCK) {
            return computer == GameOptions.SCISSORS ? 1 : -1;
        }

        if(player == GameOptions.SCISSORS) {
            return computer == GameOptions.PAPER ? 1 : -1;
        }

        if(player == GameOptions.PAPER) {
            return computer == GameOptions.ROCK ? 1 : -1;
        }

        return 0;
    }

    private void output(int result) {

        if(result == 1) {
            System.out.println("You win!");
            playerScore++;

        } else if(result == 0) {
            System.out.println("Tie!");

        } else if(result == -1) {
            System.out.println("You lose!");
            playerScore++;
        }

        numberOfPlays++;
        int ties = numberOfPlays - (playerScore + computerScore);

        System.out.println("Wins: " + playerScore + " Losses: " + computerScore + " Ties: " + ties + " Games Played: " +
                numberOfPlays);
    }

    public void run(int numberOfPlays) {

        //Player playerOne = new Player();
        Player[] playerArray = generatePlayerArray(1);


        while(numberOfPlays > 0) {

            for (int i = 0; i < playerArray.length; i++) {
                GameOptions playerChoice = generatePlayerChoice();
                playerArray[i].setPlayerChoice(playerChoice);
                GameOptions computerChoice = generatePlayerChoice();
                System.out.println("Player chose: " + playerArray[i].getPlayerChoice() + " and the computer chose: " + computerChoice);
                output(compare(playerArray[i].getPlayerChoice(), computerChoice));

                System.out.println();
                numberOfPlays--;
            }
        }

        if(getPlayerScore() > getComputerScore()) {
            System.out.println("YOU WIN!!! Games Played: " + getNumberOfPlays() + "Wins: " + getPlayerScore() + " | Losses: " + getComputerScore() + " | Ties: " + (getNumberOfPlays()-(getPlayerScore()+getComputerScore())));

        } else {
            System.out.println("YOU LOST!!! " + "Wins: " + getPlayerScore() + " | Losses: " + getComputerScore() + " | Ties: " + (getNumberOfPlays()-(getPlayerScore()+getComputerScore())));
        }

    }

    private int getComputerScore() {
        return computerScore;
    }

    private int getPlayerScore() {
        return playerScore;
    }

    private int getNumberOfPlays() {
        return numberOfPlays;
    }
}