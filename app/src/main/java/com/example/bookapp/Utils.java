package com.example.bookapp;


import java.util.ArrayList;

public class Utils {

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;


    //because we will implement singleton pattern we need to set the constructor to private
    private Utils() {
        if (allBooks == null) {
            allBooks = new ArrayList<>();
            //initializing starting data for the list of books
            //so whenever we call the Utils constructor we will have some data inside because of this method
            InitData();
        }

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

        //adding the books to an array list and setting it to adapter
        String longDesc = "The novel is a story of how a woman named Aomame begins to notice strange changes occurring in the world." + "\n" +
                "She is quickly caught up in a plot involving Sakigake, a religious cult, and her childhood love, Tengo," + "\n" +
                "and embarks on a journey to discover what is real." + "\n" +
                "This is some dummy text." + "\n" + "She is quickly caught up in a plot involving Sakigake"
                + "\n" + "She is quickly caught up in a plot involving Sakigake";

        allBooks.add(new Book(1, "1Q84", "Haruki Murakami", 1350, "https://s2.adlibris.com/images/2086196/1q84.jpg",
                "A work of maddening brilliance", longDesc));


        allBooks.add(new Book(2, "The myth of Sisyphus", "Albert Camus", 250, "https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/820e6a44067829.5806a364b49f8.jpg",
                "One of the most influential works of this century, this is a crucial exposition of existentialist thought.", "Long description"));
    }


    //getter of the instance
    public static synchronized Utils getInstance() {
        if (instance == null)
            instance = new Utils();
        return instance;
    }


    //getters
    public static ArrayList<Book> getAllBooks() {
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

    public boolean addToCurrentlyReading(Book book){return getCurrentlyReadingBooks().add(book);}
}
