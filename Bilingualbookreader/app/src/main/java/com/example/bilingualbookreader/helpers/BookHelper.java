package com.example.bilingualbookreader.helpers;
import com.example.bilingualbookreader.models.Section;
import com.example.bilingualbookreader.models.Sentence;
import com.example.bilingualbookreader.models.Word;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.domain.SpineReference;
import nl.siegmann.epublib.epub.EpubReader;

public class BookHelper {
    // Find a sentence containing the words in parameters
    public static Sentence findSentence(Book book, String[] wordsToFind) {
        InputStream inputStream = null;
        Sentence foundSentence = null;

        Spine spine = new Spine(book.getTableOfContents());

        // Get book content
        StringBuilder sb = new StringBuilder();
        for (SpineReference bookSection : spine.getSpineReferences()) {
            Resource res = bookSection.getResource();

            try {
                inputStream = res.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }

                Section section = new Section(sb.toString());
                int currentOccurences;
                int lastOccurences = 0;

                for (Sentence s : section.getSentences()) {
                    ArrayList<Word> sentenceWords = s.getWords();
                    currentOccurences = 0;
                    for (Word w : sentenceWords) {
                        currentOccurences += w.founds(wordsToFind).size();
                    }
                    if (currentOccurences > lastOccurences) {
                        lastOccurences = currentOccurences;
                        foundSentence = s;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

        return foundSentence;
    }

    public static Book openBook(String ebookFilePath) {
        EpubReader epubReader = new EpubReader();
        FileInputStream fileInputStream = null;
        Book book = null;

        try {
            fileInputStream = new FileInputStream(ebookFilePath);
            book = epubReader.readEpub(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    public static String getAllContent(Book book) {
        InputStream inputStream = null;

        Spine spine = new Spine(book.getTableOfContents());

        StringBuilder sb = new StringBuilder();
        for (SpineReference bookSection : spine.getSpineReferences()) {
            Resource res = bookSection.getResource();
            try {
                inputStream = res.getInputStream();

                BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return sb.toString();
    }
}


