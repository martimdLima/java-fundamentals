package org.academiadecodigo.whiledlings.calculator;

public class Calculator {

    public int sum(int num1, int num2){

        if(num1 < 0 || num2 < 0) {
            throw new IllegalArgumentException();
        }
        return num1 + num2;
    }
}
