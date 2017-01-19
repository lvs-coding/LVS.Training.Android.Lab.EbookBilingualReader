package com.example.bilingualbookreader.models;

import java.util.ArrayList;

/**
 * Created by laurent on 1/17/17.
 */

public class Sentence {
    private String mSentence;
    private int mStartIndex;
    private ArrayList<Word> mWords;
    private ArrayList<int[]> matchIndexes;

    public Sentence(String sentence, int startIndex) {
        if(sentence == null  || sentence.trim() == "") {
            throw new IllegalArgumentException("Sentence not valid");
        }
        mSentence = sentence;
        mStartIndex = startIndex;
        mWords = new ArrayList<>();

        int wordStartIndex = startIndex;
        for(String word:sentence.split(" ")) {
            mWords.add(new Word(word,wordStartIndex));
            wordStartIndex += word.length();
        }
    }

    public int getStartIndex() {
        return mStartIndex;
    }


    public ArrayList<Word> getWords () {
        return  mWords;
    }

    public String toString() {
        return mSentence;
    }

    public void setMatchIndexes(ArrayList<String> wordsList) {

    }
}