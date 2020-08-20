package com.martimlima.javaprojects.basic_oop.rock_paper_scissors_v2;

public class GameEngine {

    private String cpu;

    public GameEngine() {
        this.cpu = generateCPUChoice();
    }

    private String generateCPUChoice() {

        int randomInt = Randomizer.generateRandomIntBetweenRange(1, 4);

        String cpuChoice = null;

        switch (randomInt) {
            case 1:
                cpuChoice = GameOptions.ROCK.toString();
                break;
            case 2:
                cpuChoice = GameOptions.PAPER.toString();
                break;
            case 3:
                cpuChoice = GameOptions.SCISSORS.toString();
                break;
        }

        return cpuChoice;

    }

    public String getCpu() {
        return cpu;
    }

    public void generateOutcome(int numberOfTries) {

        Player playerOne = new Player();
        playerOne.GeneratePlayerChoice();
        boolean playerWon = false;

        while(numberOfTries > 0) {

            String playerChoice = playerOne.GeneratePlayerChoice().toString();
            generateCPUChoice();
            String cpuChoice = getCpu();

            System.out.println("CPU chose: " + cpuChoice);
            System.out.println("PLAYER chose: " + playerChoice);

            if (cpuChoice.equals("ROCK") && playerChoice.equals("PAPER")) {
                playerWon = true;
            } else if (cpuChoice.equals("PAPER") && playerChoice.equals("SCISSORS")) {
                playerWon = true;
            } else if (cpuChoice.equals("SCISSORS") && playerChoice.equals("ROCK")) {
                playerWon = true;
            }

            if (cpuChoice.equals(playerChoice)) {

                System.out.println("It's a draw");
            }

            if (playerWon) {
                System.out.println("You win");
            } else {
                System.out.println("You lose");
            }
            numberOfTries--;
        }

    }
}

