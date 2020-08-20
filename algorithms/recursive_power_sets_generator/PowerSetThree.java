package com.martimlima.javaprojects.algorithms.recursive_power_sets_generator;

import java.util.ArrayList;

public class PowerSetThree {

    static ArrayList<ArrayList<String>> getSubset(String[] set, int index) {

        ArrayList<ArrayList<String> > allSubsets;

        if (index < 0) {
            allSubsets = new ArrayList<ArrayList<String> >();
            allSubsets.add(new ArrayList<String>());
        } else {
            allSubsets = getSubset(set, index - 1);
            String item = set[index];
            ArrayList<ArrayList<String>> moreSubsets = new ArrayList<ArrayList<String> >();

            for (ArrayList<String> subset : allSubsets) {
                ArrayList<String> newSubset = new ArrayList<String>();
                newSubset.addAll(subset);
                newSubset.add(item);
                moreSubsets.add(newSubset);
            }
            allSubsets.addAll(moreSubsets);
        }

        return allSubsets;
    }
}
