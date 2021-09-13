package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        RecyclerView recViewFavorites = findViewById(R.id.recViewFavorites);

        //make the adapter
        BookRecyclerViewAdapter adapter = new BookRecyclerViewAdapter(this,"Favorites");

        //fill it with data
        adapter.set_books(Utils.getInstance(this).getFavoriteBooks());

        //set the adapter
        recViewFavorites.setAdapter(adapter);

        //set the LinearLayoutManager()
        recViewFavorites.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FavoritesActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}