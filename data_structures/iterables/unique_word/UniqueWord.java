package com.martimlima.javaprojects.data_structures.iterables.unique_word;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UniqueWord implements Iterable<String> {

    private Set<String> words = new HashSet<String>();

    public UniqueWord(String s) {

        //Collections.addAll(words, s.split(" ");
        for (String word : s.split(" ")) {
            words.add(word);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return words.iterator();
    }
}
