package com.example.bookapp;


import androidx.annotation.NonNull;

public class Book {
    private int _id;
    private String _name;
    private String _author;
    private int _pages;
    private String _imageURL;
    private String _shortDesc;
    private String _longDesc;


    //constructor
    public Book(int _id, String _name, String _author, int _pages, String _imageURL, String _shortDesc, String _longDesc) {
        this._id = _id;
        this._name = _name;
        this._author = _author;
        this._pages = _pages;
        this._imageURL = _imageURL;
        this._shortDesc = _shortDesc;
        this._longDesc = _longDesc;
    }


    //_id getters and setters
    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }

    //name getters and setters
    public String get_name() {
        return _name;
    }
    public void set_name(String _name) {
        this._name = _name;
    }


    //_author getters and setters
    public String get_author() {
        return _author;
    }
    public void set_author(String _author) {
        this._author = _author;
    }


    //_pages getters and setters
    public int get_pages() {
        return _pages;
    }
    public void set_pages(int _pages) {
        this._pages = _pages;
    }

    //_imageURL getters and setters
    public String get_imageURL() {
        return _imageURL;
    }
    public void set_imageURL(String _imageURL) {
        this._imageURL = _imageURL;
    }

    //_shortDesc getters and setters
    public String get_shortDesc() {
        return _shortDesc;
    }
    public void set_shortDesc(String _shortDesc) {
        this._shortDesc = _shortDesc;
    }


    //_longDesc getters and setters
    public String get_longDesc() {
        return _longDesc;
    }
    public void set_longDesc(String _longDesc) {
        this._longDesc = _longDesc;
    }

    @Override
    public String toString() {
        return "Book{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _author='" + _author + '\'' +
                ", _pages=" + _pages +
                ", _imageURL='" + _imageURL + '\'' +
                ", _shortDesc='" + _shortDesc + '\'' +
                ", _longDesc='" + _longDesc + '\'' +
                '}';
    }
}

