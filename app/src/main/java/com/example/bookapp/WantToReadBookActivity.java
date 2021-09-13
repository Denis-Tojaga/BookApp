package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class WantToReadBookActivity extends AppCompatActivity {

    private RecyclerView recViewWantToReadBooks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read_book);


        recViewWantToReadBooks = findViewById(R.id.recViewWantToReadBooks);

        //first we need to initialize the adapter
        BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this, "WantToRead");

        //then we need to fill it with data
        adapter.set_books(Utils.getInstance(this).getWantToReadBooks());

        //after that we set the adapter to recyclerView
        recViewWantToReadBooks.setAdapter(adapter);

        //then we just set the LayoutManager to this recyclerView
        recViewWantToReadBooks.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}