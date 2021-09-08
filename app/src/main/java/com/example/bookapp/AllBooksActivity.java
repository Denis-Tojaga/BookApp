package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView _booksRecView;
    private BookRecyclerViewAdapter _bookRecViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);


        //because we are inside an activity we can send this
        //initializing the bookRecViewAdapter and bookRecView
        _booksRecView = findViewById(R.id.recViewBooks);
        _bookRecViewAdapter = new BookRecyclerViewAdapter(this,"AllBooks");

        //we use an ArrayList from the Utils class instance because its static
        _bookRecViewAdapter.set_books(Utils.getInstance().getAllBooks());


        //binding adapter with the recyclerView and setting a LayoutManager
        _booksRecView.setAdapter(_bookRecViewAdapter);

        //everytime we make our custom recyclerView we need to set the LayoutManager
        _booksRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}