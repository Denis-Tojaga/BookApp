package com.example.bookapp;


import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private static final String ALL_BOOKS_KEY = "all_books";

    private SharedPreferences sharedPreferences;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;


    //because we will implement singleton pattern we need to set the constructor to private
    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);

        if (getAllBooks() == null)
            InitData();

        if (alreadyReadBooks == null)
            alreadyReadBooks = new ArrayList<>();

        if (wantToReadBooks == null)
            wantToReadBooks = new ArrayList<>();

        if (currentlyReadingBooks == null)
            currentlyReadingBooks = new ArrayList<>();

        if (favoriteBooks == null)
            favoriteBooks = new ArrayList<>();
    }


    //filling the arrayList with books
    private void InitData() {
        //TODO: add initial data

        ArrayList<Book> books = new ArrayList<>();


        //adding the books to an array list and setting it to adapter
        String longDesc = "The novel is a story of how a woman named Aomame begins to notice strange changes occurring in the world." + "\n" +
                "She is quickly caught up in a plot involving Sakigake, a religious cult, and her childhood love, Tengo," + "\n" +
                "and embarks on a journey to discover what is real." + "\n" +
                "This is some dummy text." + "\n" + "She is quickly caught up in a plot involving Sakigake"
                + "\n" + "She is quickly caught up in a plot involving Sakigake";

        books.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://s2.adlibris.com/images/2086196/1q84.jpg",
                "A work of maddening brilliance", longDesc));


        books.add(new Book(2, "The myth of Sisyphus", "Albert Camus", 250, "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/820e6a44067829.5806a364b49f8.jpg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought.", "Long description"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();
    }


    //getter of the instance
    public static synchronized Utils getInstance(Context context) {
        if (instance == null)
            instance = new Utils(context);
        return instance;
    }


    //getters
    public ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> allBooks = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return allBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }


    public Book getBookByID(int bookId) {
        for (Book item : allBooks)
            if (item.get_id() == bookId)
                return item;
        return null;
    }


    //methods for adding elements to arrayLists
    public boolean addToAlreadyReadBooksList(Book book) {
        //this method returns boolean value, whether the book is added successfully or not
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book) {
        return wantToReadBooks.add(book);
    }

    public boolean addToFavorites(Book book) {
        return getFavoriteBooks().add(book);
    }

    public boolean addToCurrentlyReading(Book book) {
        return getCurrentlyReadingBooks().add(book);
    }


    //methods for deleting elements from arrayLists
    public boolean removeFromAlreadyRead(Book removedBook) {
        return alreadyReadBooks.remove(removedBook);
    }

    public boolean removeFromWantToRead(Book removedBook) {
        return wantToReadBooks.remove(removedBook);
    }


    public boolean removeFromCurrentlyReading(Book removedBook) {
        return currentlyReadingBooks.remove(removedBook);
    }

    public boolean removeFromFavorites(Book removedBook) {
        return favoriteBooks.remove(removedBook);
    }
}
