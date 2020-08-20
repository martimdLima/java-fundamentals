package com.martimlima.javaprojects.data_structures.iterables.word_histogram;

public class Main {
    public static final String STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor" +
                                        "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis" +
                                        "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                                        "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore" +
                                        "eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt" +
                                        "in culpa qui officia deserunt mollit anim id est laborum.";

    public static void main(String[] args) {

        WordHistogramComposition wordHistogram = new WordHistogramComposition(STRING);
        System.out.println("Word Histogram detected " + wordHistogram.size() + " distinct words:");

        for (String word: wordHistogram) {

            System.out.println(word + " : " + wordHistogram.get(word));

        }

    }
}
