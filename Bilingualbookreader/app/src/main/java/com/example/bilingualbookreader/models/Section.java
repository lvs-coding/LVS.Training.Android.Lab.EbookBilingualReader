package com.example.bilingualbookreader.models;

import java.util.ArrayList;

/**
 * Created by laurent on 1/17/17.
 */

public class Section {
    private ArrayList<Sentence> sentences;

    public Section (String text) {
        sentences = new ArrayList<>();

        if(text == null || text.trim() == "") {
            throw new IllegalArgumentException("Text not valid");
        }
        String formattedText = text.trim().replaceAll("[^a-zA-Z. ]", "").toLowerCase();
        String[] sentencesArray = formattedText.split("\\.");
        int startIndex;
        int currentIndex = 0;
        for(String sentenceStr:sentencesArray) {

            startIndex = currentIndex;
            if(sentenceStr.trim() != "") {
                sentences.add(new Sentence(sentenceStr,startIndex));
            }
            currentIndex += sentenceStr.length();
        }
    }

    public ArrayList<Sentence> getSentences() {
        return sentences;
    }

}
