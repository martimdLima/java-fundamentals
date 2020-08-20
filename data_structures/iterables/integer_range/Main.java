package com.martimlima.javaprojects.data_structures.iterables.integer_range;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

       IntegerRange r = new IntegerRange(-10, 10);

        Iterator<Integer> it = r.iterator();

        while (it.hasNext()) {
            int i = it.next();
            if (i == 1 || i == 2 || i == 3) {
                it.remove();
            }
        }

        for (Integer i : r) {
            System.out.println("Iterated Element: " + i);
        }
    }
}
