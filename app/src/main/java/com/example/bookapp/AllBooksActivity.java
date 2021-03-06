package com.example.bookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView _booksRecView;
    private BookRecyclerViewAdapter _bookRecViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        //setting up the home button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //because we are inside an activity we can send this
        //initializing the bookRecViewAdapter and bookRecView
        _booksRecView = findViewById(R.id.recViewBooks);
        _bookRecViewAdapter = new BookRecyclerViewAdapter(this,"AllBooks");

        //we use an ArrayList from the Utils class instance because its static
        _bookRecViewAdapter.set_books(Utils.getInstance(this).getAllBooks());


        //binding adapter with the recyclerView and setting a LayoutManager
        _booksRecView.setAdapter(_bookRecViewAdapter);

        //everytime we make our custom recyclerView we need to set the LayoutManager
        _booksRecView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                //navigating the user back to the main activity
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}