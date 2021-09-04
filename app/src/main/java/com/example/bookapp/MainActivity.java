package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //initializing all UI elements in order to manipulate with their data
    private Button btnAllBooks, btnAlreadyRead, btnCurrentlyReading, btnYourWishlist, btnFavorite, btnAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //initializing all needed views
        InitViews();
    }

    private void InitViews() {

        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnYourWishlist = findViewById(R.id.btnYourWishlist);
        btnFavorite = findViewById(R.id.btnSeeFavorites);
        btnAbout = findViewById(R.id.btnAbout);

    }

    public void btnOnClickOpenAllBooks(View view) {
        Intent intent = new Intent(MainActivity.this,AllBooksActivity.class);
        startActivity(intent);
    }
}