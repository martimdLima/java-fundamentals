package org.academiadecodigo.whiledlings.calculator;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setup(){
        calculator = new Calculator();
    }

    @Test
    public void sum_PositiveNumbers_ReturnsPositive() {

        final int expected = 4;

        final int result = calculator.sum(2, 2);

        assertEquals(result, expected);
    }

    @Test
    public void sum_Any_Positive_Integers() {

        final int expected1 = 10;
        final int result1 = calculator.sum(5, 5);

        final int result2 = calculator.sum(10, 10);
        final int expected2 = 20;

        final int expected3 = 40;
        final int result3 = calculator.sum(20, 20);

        assertEquals("fail sum 5 + 5", expected1, result1);
        assertEquals("fail sum 10 + 10", expected2, result2);
        assertEquals("fail sum 20 + 20", expected3, result3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void sum_PositiveNumbers_with_NegativeNumbers(){

        calculator.sum(-1,1);

//        try {
//            calculator.sum(-1,1);
//        } catch (IllegalArgumentException ex) {
//            return;
//        }
//        fail("Fail to catch the exception");
    }


    @After
    public void tearDown() {
        calculator = null;
    }
}
