package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;

public class BookDetailsActivity extends AppCompatActivity {

    private ImageView imgBookImage;
    private Button btnAddToCurrentlyReading,btnAddToWantToRead,btnAddToAlreadyRead,btnAddToFavorites;
    private TextView txtBookName,txtBookAuthor,txtPages,txtShortDescription,txtLongDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);


        //initializing all the views in this activity
        InitViews();

        //in order to get data from the intent, if we sent serializable data we need to use getSerializableExtra() method who takes only the type of the object
        //since we get the Serializable object from this method we need to cast it to out needed object
        Book clickedBook = (Book)getIntent().getSerializableExtra("Book");
        setBookData(clickedBook);
    }

    private void setBookData(Book _clickedBook) {
        txtBookName.setText(_clickedBook.get_name());
        txtBookAuthor.setText(_clickedBook.get_author());
        txtPages.setText(_clickedBook.get_pages());
        txtShortDescription.setText(_clickedBook.get_shortDesc());
        txtLongDescription.setText(_clickedBook.get_longDesc());
        Glide.with(this).asBitmap().load(_clickedBook.get_imageURL()).into(imgBookImage);
    }


    private void InitViews() {
        imgBookImage = findViewById(R.id.imgBookImage);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToWantToRead = findViewById(R.id.btnAddToWantToRead);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
        txtBookName = findViewById(R.id.txtBookName);
        txtBookAuthor = findViewById(R.id.txtBookAuthor);
        txtPages = findViewById(R.id.txtPages);
        txtShortDescription = findViewById(R.id.txtShortBookDescription);
        txtLongDescription = findViewById(R.id.txtLongBookDescription);
    }




}