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
        _bookRecViewAdapter = new BookRecyclerViewAdapter(this);




        //adding the books to an array list and setting it to adapter
        ArrayList<Book> _books = new ArrayList<>();
        _books.add(new Book(1,"1Q84","Haruki Murakami",1350,"https://s2.adlibris.com/images/2086196/1q84.jpg",
                            "A work of maddening brilliance","Long description"));
        _books.add(new Book(2,"The myth of Sisyphus","Albert Camus",250,"https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/820e6a44067829.5806a364b49f8.jpg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought.","Long description"));

        _bookRecViewAdapter.set_books(_books);




        //binding adapter with the recyclerView and setting a LayoutManager
        _booksRecView.setAdapter(_bookRecViewAdapter);
        _booksRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}