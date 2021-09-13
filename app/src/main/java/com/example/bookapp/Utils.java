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
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVORITE_BOOKS = "favorite_books";


    private SharedPreferences sharedPreferences;

  /*  private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;*/


    //because we will implement singleton pattern we need to set the constructor to private
    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getAllBooks() == null)
            InitData();

        if (getAlreadyReadBooks() == null) {
            editor.putString(ALREADY_READ_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (getWantToReadBooks() == null) {
            editor.putString(WANT_TO_READ, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (getCurrentlyReadingBooks() == null) {
            editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }

        if (getFavoriteBooks() == null) {
            editor.putString(FAVORITE_BOOKS, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
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

    public ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> alreadyReadBooks = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS, null), type);
        return alreadyReadBooks;
    }

    public ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> wantToReadBooks = gson.fromJson(sharedPreferences.getString(WANT_TO_READ, null), type);
        return wantToReadBooks;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> currentlyReading = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS, null), type);
        return currentlyReading;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> favorite = gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS, null), type);
        return favorite;
    }


    public Book getBookByID(int bookId) {
        ArrayList<Book> books = getAllBooks();

        if (books != null)
            for (Book item : books)
                if (item.get_id() == bookId)
                    return item;
        return null;
    }


    //methods for adding elements to arrayLists
    public boolean addToAlreadyReadBooksList(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ);
                editor.putString(WANT_TO_READ, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean addToFavorites(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS);
                editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }

    public boolean addToCurrentlyReading(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null) {
            if (books.add(book)) {
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                editor.commit();
                return true;
            }
        }

        return false;
    }


    //methods for deleting elements from arrayLists
    public boolean removeFromAlreadyRead(Book removedBook) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.get_id() == removedBook.get_id()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean removeFromWantToRead(Book removedBook) {
        ArrayList<Book> books = getWantToReadBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.get_id() == removedBook.get_id()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ);
                        editor.putString(WANT_TO_READ, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean removeFromCurrentlyReading(Book removedBook) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.get_id() == removedBook.get_id()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean removeFromFavorites(Book removedBook) {
        ArrayList<Book> books = getFavoriteBooks();
        if (books != null) {
            for (Book b : books) {
                if (b.get_id() == removedBook.get_id()) {
                    if (books.remove(b)) {
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS);
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
