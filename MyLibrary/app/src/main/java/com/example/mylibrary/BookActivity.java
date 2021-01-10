package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    ImageView imgBook;
    Button btnAddToCurrentlyReading, btnAddToWishList, btnAddToAlreadyRead, btnAddToFavorites;
    TextView txtBookName, txtAuthor, txtPages, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();
        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
            if (bookId != -1){
                Book incomingBook = Utils.getInstance().getBookById(bookId);
                if (incomingBook != null) {
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavoriteBooks(incomingBook);
                }
            }
        }


    }

    private void handleFavoriteBooks(final Book incomingBook) {
        ArrayList<Book> favoriteBooks = Utils.getInstance().getFavoriteBooks();
        boolean bookFavorite = false;
        for (Book b: favoriteBooks){
            if (b.getId() == incomingBook.getId()){
                bookFavorite = true;
                break;
            }
        }
        if (bookFavorite){
            btnAddToFavorites.setEnabled(false);
        }else {
            btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToFavorites(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, FavoriteBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Book adding failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void handleCurrentlyReadingBooks(final Book incomingBook) {
        ArrayList<Book> currentlyReadBooks = Utils.getInstance().getCurrentlyReadingBooks();
        boolean bookCurrentlyRead = false;
        for (Book b: currentlyReadBooks){
            if (b.getId() == incomingBook.getId()){
                bookCurrentlyRead = true;
                break;
            }
        }
        if (bookCurrentlyRead){
            btnAddToCurrentlyReading.setEnabled(false);
        }else {
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToCurrentlyReadingBooks(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, CurrentlyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Book adding failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void handleWantToReadBooks(final Book incomingBook) {
        ArrayList<Book> wantToReadBooks = Utils.getInstance().getWantToReadBooks();
        boolean bookWantToRead = false;
        for (Book b: wantToReadBooks){
            if (b.getId() == incomingBook.getId()){
                bookWantToRead = true;
                break;
            }
        }
        if (bookWantToRead){
            btnAddToWishList.setEnabled(false);
        }else {
            btnAddToWishList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToWantToRead(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, WantToReadActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Book adding failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    /**
     * Enable and disable button,
     * Add the book to Already Read Book ArrayList
     * @param incomingBook
     */
    private void handleAlreadyRead(final Book incomingBook) {
        ArrayList<Book> alreadyReadBooks = Utils.getInstance().getAlreadyReadBooks();
        boolean bookAlreadyRead = false;
        for (Book b: alreadyReadBooks){
            if (b.getId() == incomingBook.getId()){
                bookAlreadyRead = true;
                break;
            }
        }
        if (bookAlreadyRead){
            btnAddToAlreadyRead.setEnabled(false);
        }else {
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.getInstance().addToAlreadyRead(incomingBook)){
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this, AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(BookActivity.this, "Book adding failed", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void setData(Book book) {
        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtDescription.setText(book.getLongDesc());
        Glide.with(this).asBitmap().load(book.getImageUrl()).into(imgBook);
    }

    private void initViews() {
        imgBook = findViewById(R.id.imgBook);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToWishList = findViewById(R.id.btnAddToWishList);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorite);
        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtAuthor = findViewById(R.id.txtAuthorName);
        txtDescription = findViewById(R.id.txtDescriptionContent);
    }



}