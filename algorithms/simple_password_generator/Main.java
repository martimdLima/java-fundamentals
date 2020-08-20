package com.martimlima.javaprojects.algorithms.simple_password_generator;

import static com.martimlima.javaprojects.algorithms.simple_password_generator.PasswordGenerator.crack;

public class Main {
    public static void main(String[] args) {

        char arr[] = {'a', 'b', 'c', 'd', 'e'};
        int len = arr.length;
        crack(arr, len);
    }
}
