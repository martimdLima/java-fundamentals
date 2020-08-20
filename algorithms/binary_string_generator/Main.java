package com.martimlima.javaprojects.algorithms.binary_string_generator;

import static com.martimlima.javaprojects.algorithms.binary_string_generator.BinaryStringGenerator.generateAllBinaryStrings;

public class Main {

    public static void main(String[] args) {

        //int n = 4;
        //int[] arr = new int[n];

        // Print all binary strings
        generateAllBinaryStrings(8, new int[8], 0);
    }
}
