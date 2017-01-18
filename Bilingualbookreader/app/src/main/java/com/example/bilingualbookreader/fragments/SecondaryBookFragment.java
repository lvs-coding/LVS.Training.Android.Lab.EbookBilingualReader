package com.example.bilingualbookreader.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.bilingualbookreader.R;
import com.example.bilingualbookreader.helpers.BookHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Spine;
import nl.siegmann.epublib.domain.SpineReference;

/**
 * Created by laurent on 1/17/17.
 */

public class SecondaryBookFragment extends Fragment {
    private Book book;
    private static final String EBOOK_FILE_PATH = "/sdcard/Download/the_little_prince_en.epub";
    WebView wv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_book, container, false);


        book = BookHelper.openBook(EBOOK_FILE_PATH);
        wv = (WebView)view.findViewById(R.id.wv_book);

        String bookContent = BookHelper.getAllContent(book);

        wv.loadDataWithBaseURL(null, bookContent, "text/html", "utf-8", null);



        // Inflate the layout for this fragment
        return view;
    }

}