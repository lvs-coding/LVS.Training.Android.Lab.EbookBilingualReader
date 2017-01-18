package com.example.bilingualbookreader.models;

import java.util.ArrayList;

/**
 * Created by laurent on 1/17/17.
 */

public class Word {
    private String mWord;
    private int mStartIndex;

    public Word(String word, int startIndex) {
        mWord = word;
        mStartIndex = startIndex;
    }

    public  String toString() {
        return mWord;
    }

    public ArrayList<Integer> founds(String[] wordsList) {
        ArrayList<Integer> occurences = new ArrayList<>();
        int index = 0;
        for(String word:wordsList) {
            if (mWord.equals(word)) {
                occurences.add(index);
            }
            index++;
        }
        return  occurences;
    }
}
