package com.example.bookapp;


import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private static ArrayList<Book> allBooks;


    //because we will implement singleton pattern we need to set the constructor to private
    private Utils() {
        if(allBooks==null)
        {
            allBooks = new ArrayList<>();
            
        }

    }


    //getter of the instance
    public static synchronized Utils getInstance() {
        if(instance==null)
            instance = new Utils();
        return instance;
    }
}
