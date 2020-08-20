package com.martimlima.javaprojects.algorithms.recursive_power_sets_generator;

public class PowerSetOne {

    // str : Stores input string
    // currentSubSet : Stores current subset
    // index : Index in current subset, currentSubSet
    static void powerSet(String str, int index, String currentSubSet) {
        int n = str.length();

        // base case
        if (index == n) {
            return;
        }

        // First print current subset
        System.out.println(currentSubSet);

        // Try appending remaining characters
        // to current subset
        for (int i = index + 1; i < n; i++) {
            currentSubSet += str.charAt(i);
            powerSet(str, i, currentSubSet);

            // Once all subsets beginning with
            // initial "currentSubSet" are printed, remove
            // last character to consider a different
            // prefix of subsets.
            currentSubSet = currentSubSet.substring(0, currentSubSet.length() - 1);
        }
    }
}
