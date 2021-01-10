package com.example.mylibrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {
    // singleton pattern

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS_KEY = "already_read_books";
    private static final String WANT_TO_READ_BOOKS_KEY = "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS_KEY = "currently_reading_books";
    private static final String FAVORITE_BOOKS_KEY = "favorite_books";

    private static Utils instance;

    private SharedPreferences sharedPreferences;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getAllBooks() == null) {
            initData();
        }
        if (null == getAlreadyReadBooks()) {
            editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getWantToReadBooks()) {
            editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentlyReadingBooks()) {
            editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavoriteBooks()) {
            editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {
        ArrayList<Book> books = new ArrayList<>();
        // only add reference to the ArrayList
        books.add(new Book(1, "1Q84", "Haruki Murakami", 130, "https://images-na.ssl-images-amazon.com/images/I/81M0jxrDz5L.jpg", "A work of madding brilliance", "1Q84 is a dystopian novel written by Japanese writer Haruki Murakami, first published in three volumes in Japan in 2009–10. It covers a fictionalized year of 1984 in parallel with a \"real\" one. The novel is a story of how a woman named Aomame begins to notice strange changes occurring in the world."));
        books.add(new Book(2, "Effective Java", "Joshua Bloch", 150,
                "https://images-na.ssl-images-amazon.com/images/I/51wl8cINKYL.jpg", "A work of Java",
                "The Definitive Guide to Java Platform Best Practices–Updated for Java 7, 8, and 9 Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java."));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit(); // apply immediately
    }

    public  ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY, null), type);
        return books;
    }

    public  ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS_KEY, null), type);
        return books;
    }

    public  ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS_KEY, null), type);
        return books;
    }

    public ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books =gson.fromJson(sharedPreferences.getString(FAVORITE_BOOKS_KEY, null), type);
        return books;
    }

    public static synchronized Utils getInstance(Context context) {
        if (null == instance) {
            instance = new Utils(context);
        }
        return instance;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = getAllBooks();
        if (null != books){
            for (Book b : books) { // b: reference
                if (b.getId() == id) {
                    // Book book = new Book(b); // copy
                    // return book;
                    return b;
                }
            }
        }
        return null;

    }

    public boolean addToAlreadyRead(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(ALREADY_READ_BOOKS_KEY);
                editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToRead(Book book) {
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS_KEY);
                editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavorites(Book book) {
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(FAVORITE_BOOKS_KEY);
                editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReadingBooks(Book book) {
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books){
            if (books.add(book)){
                Gson gson = new Gson();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS_KEY);
                editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeAlreadyReadBook(Book book) {
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(ALREADY_READ_BOOKS_KEY);
                        editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;

                    }
                }

            }
        }
        return false;
    }

    public boolean removeWantToReadBook(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS_KEY);
                        editor.putString(WANT_TO_READ_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;

                    }
                }

            }
        }
        return false;
    }

    public boolean removeFavoriteBook(Book book){
        ArrayList<Book> books = getFavoriteBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(FAVORITE_BOOKS_KEY);
                        editor.putString(FAVORITE_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;

                    }
                }

            }
        }
        return false;
    }

    public boolean removeCurrentlyReadingBooks(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        Gson gson = new Gson();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS_KEY);
                        editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));
                        editor.commit();
                        return true;

                    }
                }

            }
        }
        return false;
    }
}
