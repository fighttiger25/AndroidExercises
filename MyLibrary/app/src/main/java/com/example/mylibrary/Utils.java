package com.example.mylibrary;

import java.util.ArrayList;

public class Utils {
    // singleton pattern

    private static Utils instance;

    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favoriteBooks;

    private Utils() {
        if (allBooks == null) {
            allBooks = new ArrayList<>();
            initData();
        }
        if (null == alreadyReadBooks) {
            alreadyReadBooks = new ArrayList<>();
        }
        if (null == wantToReadBooks) {
            wantToReadBooks = new ArrayList<>();
        }
        if (null == currentlyReadingBooks) {
            currentlyReadingBooks = new ArrayList<>();
        }
        if (null == favoriteBooks) {
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initData() {
        // only add reference to the ArrayList
        allBooks.add(new Book(1, "1Q84", "Haruki Murakami", 130, "https://images-na.ssl-images-amazon.com/images/I/81M0jxrDz5L.jpg", "A work of madding brilliance", "1Q84 is a dystopian novel written by Japanese writer Haruki Murakami, first published in three volumes in Japan in 2009–10. It covers a fictionalized year of 1984 in parallel with a \"real\" one. The novel is a story of how a woman named Aomame begins to notice strange changes occurring in the world."));
        allBooks.add(new Book(2, "Effective Java", "Joshua Bloch", 150,
                "https://images-na.ssl-images-amazon.com/images/I/51wl8cINKYL.jpg", "A work of Java",
                "The Definitive Guide to Java Platform Best Practices–Updated for Java 7, 8, and 9 Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java."));
    }

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

    public static synchronized Utils getInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }

    public Book getBookById(int id) {
        for (Book b : allBooks) { // b: reference
            if (b.getId() == id) {
                Book book = new Book(b); // copy
                return book;
            }
        }
        return null;
    }

    public boolean addToAlreadyRead(Book book) {
        return alreadyReadBooks.add(book);
    }

    public boolean addToWantToRead(Book book) {
        return wantToReadBooks.add(book);
    }

    public boolean addToFavorites(Book book) {
        return favoriteBooks.add(book);
    }

    public boolean addToCurrentlyReadingBooks(Book book) {
        return currentlyReadingBooks.add(book);
    }

    public boolean removeAlreadyReadBook(Book book) {
        return alreadyReadBooks.remove(book);
    }

    public boolean removeWantToReadBook(Book book){
        return wantToReadBooks.remove(book);
    }

    public boolean removeFavoriteBook(Book book){
        return favoriteBooks.remove(book);
    }

    public boolean removeCurrentlyReadingBooks(Book book){
        return currentlyReadingBooks.remove(book);
    }
}
