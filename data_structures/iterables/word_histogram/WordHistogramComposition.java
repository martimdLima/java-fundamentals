package com.martimlima.javaprojects.data_structures.iterables.word_histogram;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WordHistogramComposition implements Iterable<String>{

    private Map<String,Integer> histogramData;

    public WordHistogramComposition(String text){
        histogramData = new HashMap<>();
        for(String word : text.split(" ")){
            histogramData.put(word, histogramData.containsKey(word) ? histogramData.get(word) + 1 : 1);
        }
    }

    public int size(){
        return histogramData.size();
    }

    public int get(String word){
        return histogramData.get(word);
    }

    @Override
    public Iterator<String> iterator() {
        return histogramData.keySet().iterator();
    }

}
