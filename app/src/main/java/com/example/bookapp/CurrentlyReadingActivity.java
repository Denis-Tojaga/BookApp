package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class CurrentlyReadingActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);

        RecyclerView recViewCurrentlyReading = findViewById(R.id.recViewCurrentlyReading);

        //make the adapter
        BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this,"CurrentlyReading");
        //fill it with data
        adapter.set_books(Utils.getCurrentlyReadingBooks());
        //set the adapter to recyclerView
        recViewCurrentlyReading.setAdapter(adapter);
        //set the layoutManager
        recViewCurrentlyReading.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CurrentlyReadingActivity.this, MainActivity.class);
        //clear task and set this one as new one
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}