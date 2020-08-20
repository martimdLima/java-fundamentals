package com.martimlima.javaprojects.data_structures.iterables.unique_word;

public class Main {

    public static final String STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";

    public static void main(String[] args) {

        UniqueWord wc = new UniqueWord(STRING);

        for (String word: wc) {
            System.out.println(word);
        }

    }


}
