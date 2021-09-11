package com.example.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;

public class BookDetailsActivity extends AppCompatActivity {

    private ImageView imgBookImage;
    private Button btnAddToCurrentlyReading, btnAddToWantToRead, btnAddToAlreadyRead, btnAddToFavorites;
    private TextView txtBookName, txtBookAuthor, txtPages, txtShortDescription, txtLongDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);


        //initializing all the views in this activity
        InitViews();

        //in order to get data from the intent, if we sent serializable data we need to use getSerializableExtra() method who takes only the type of the object
        //since we get the Serializable object from this method we need to cast it to out needed object
        //otherwise we can just pass in the bookId
        ReceiveBookFromIntent();
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
    //storing the received data and placing it in corresponding fields
    private void ReceiveBookFromIntent() {

        Intent receivedIntent = getIntent();
        //getting the bookId from the intent we got
        if (receivedIntent != null) {
            int bookId = receivedIntent.getIntExtra("BookId", -1);

            if (bookId != -1) {
                Book incomingBook = Utils.getInstance().getBookByID(bookId);
                if (incomingBook != null) {
                    //setting the data inside corresponding fields
                    setBookData(incomingBook);

                    //method for handling if the book is already read
                    handleAlreadyRead(incomingBook);
                    //method for handling if the book is already marked as want to read
                    handleWantToRead(incomingBook);
                    handleCurrentlyReadingBook(incomingBook);
                    handleAddToFavoriteBook(incomingBook);
                }
            }
        }
    }
    private void setBookData(Book _clickedBook) {
        txtBookName.setText(_clickedBook.get_name());
        txtBookAuthor.setText(_clickedBook.get_author());
        txtPages.setText(_clickedBook.get_pages());
        txtShortDescription.setText(_clickedBook.get_shortDesc());
        txtLongDescription.setText(_clickedBook.get_longDesc());
        Glide.with(this).asBitmap().load(_clickedBook.get_imageURL()).into(imgBookImage);
    }







    //methods for handling clicked buttons
    private void handleAddToFavoriteBook(Book incomingBook) {
        ArrayList<Book> favoriteBooks = Utils.getInstance().getFavoriteBooks();
        boolean alreadyExists = false;

        //checking if the book alreadyExists
        for (Book item : favoriteBooks) {
            if (item.get_id() == incomingBook.get_id())
                alreadyExists = true;
        }

        setButtonVisibility(alreadyExists, incomingBook, "btnAddToFavorites");
    }

    private void handleCurrentlyReadingBook(Book incomingBook) {
        ArrayList<Book> currentlyReadBooks = Utils.getInstance().getCurrentlyReadingBooks();
        boolean alreadyExists = false;

        //checking if the book alreadyExists
        for (Book item : currentlyReadBooks) {
            if (item.get_id() == incomingBook.get_id())
                alreadyExists = true;
        }

        setButtonVisibility(alreadyExists, incomingBook, "btnAddToCurrentlyReading");
    }

    private void handleWantToRead(Book incomingBook) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();
        boolean alreadyExists = false;

        //checking if the book alreadyExists
        for (Book item : wantToReadBooks) {
            if (item.get_id() == incomingBook.get_id())
                alreadyExists = true;
        }

        setButtonVisibility(alreadyExists, incomingBook, "btnAddToWantToRead");
    }

    private void handleAlreadyRead(Book incomingBook) {

        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();
        boolean alreadyExists = false;

        //checking if the book alreadyExists
        for (Book item : alreadyReadBooks) {
            if (item.get_id() == incomingBook.get_id())
                alreadyExists = true;
        }

        setButtonVisibility(alreadyExists, incomingBook, "btnAddToAlreadyRead");
    }







    //setting the button visibility and handling onClickListener
    private void setButtonVisibility(boolean alreadyExists, Book incomingBook, String btnID) {

        Button clickedButton = null;
        String action = "unknown";

        switch (btnID) {
            case "btnAddToWantToRead":
                clickedButton = findViewById(R.id.btnAddToWantToRead);
                action = "WantToRead";
                break;

            case "btnAddToAlreadyRead":
                clickedButton = findViewById(R.id.btnAddToAlreadyRead);
                action = "AlreadyRead";
                break;

            case "btnAddToCurrentlyReading":
                clickedButton = findViewById(R.id.btnAddToCurrentlyReading);
                action = "CurrentlyReading";
                break;

            case "btnAddToFavorites":
                clickedButton = findViewById(R.id.btnAddToFavorites);
                action = "Favorites";
                break;

            case "unknown":
                break;
        }

        if (clickedButton != null)
            HandleClickedButton(clickedButton, alreadyExists, incomingBook, action);
    }

    private void HandleClickedButton(Button clickedButton, boolean alreadyExists, Book incomingBook, String action) {
        clickedButton.setEnabled(!alreadyExists);

        if (!alreadyExists) {
            clickedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //if the book is added successfully we show a toast message and navigate the user to another activity
                    HandleUserNavigation(incomingBook, action);
                }
            });
        }
    }

    private void HandleUserNavigation(Book incomingBook, String action) {
        switch (action) {


            case "AlreadyRead":
                if (Utils.getInstance().addToAlreadyReadBooksList(incomingBook)) {
                    Toast.makeText(this, "Book added to already read!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookDetailsActivity.this, AlreadyReadBookActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(BookDetailsActivity.this, "Something wrong happened try one more time!", Toast.LENGTH_SHORT).show();
                }
                break;


            case "WantToRead":
                Toast.makeText(this, "Want to read clicked!", Toast.LENGTH_SHORT).show();
                if (Utils.getInstance().addToWantToRead(incomingBook)) {
                    Toast.makeText(this, "Book added to want to read!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookDetailsActivity.this, WantToReadBookActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(BookDetailsActivity.this, "Something wrong happened try one more time!", Toast.LENGTH_SHORT).show();
                }
                break;


            case "CurrentlyReading":
                if (Utils.getInstance().addToCurrentlyReading(incomingBook)) {
                    Toast.makeText(this, "Book added to currently reading!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookDetailsActivity.this, CurrentlyReadingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(BookDetailsActivity.this, "Something wrong happened try one more time!", Toast.LENGTH_SHORT).show();
                }
                break;


            case "Favorites":
                //TODO Implement this method
                if (Utils.getInstance().addToFavorites(incomingBook)) {
                    Toast.makeText(this, "Book added to favorites!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BookDetailsActivity.this, FavoritesActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(BookDetailsActivity.this, "Something wrong happened try one more time!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}