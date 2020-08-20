package com.martimlima.javaprojects.algorithms.recursive_power_sets_generator;

import java.util.ArrayList;

import static com.martimlima.javaprojects.algorithms.recursive_power_sets_generator.PowerSetOne.powerSet;
import static com.martimlima.javaprojects.algorithms.recursive_power_sets_generator.PowerSetThree.getSubset;

public class Main {

    public static void main(String[] args) {

        // Generate_Power_Set_One
        String string = "abc";
        int index = -1;
        String current = "";
        powerSet(string, index, current);


        // Generate_Power_Set_Two
        System.out.println("\n");
        String string2 = "abc";
        int index2 = -1;
        String current2 ="";
        powerSet(string2,index2,current2);

        // Generate_Power_Set_Three
        System.out.println("\n");
        String[] set = { "a", "b", "c" };

        int index3 = set.length - 1;
        ArrayList<ArrayList<String>> result = getSubset(set, index3);
        System.out.println(result);


    }
}
