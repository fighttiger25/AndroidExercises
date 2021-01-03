package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    ImageView imgBook;
    Button btnAddToCurrentlyReading, btnAddToWishList, btnAddToAlreadyRead, btnAddToFavorites;
    TextView txtBookName, txtAuthor, txtPages, txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();
        Book book = new Book(1, "1Q84", "Haruki Murakami", 130, "https://images-na.ssl-images-amazon.com/images/I/81M0jxrDz5L.jpg", "A work of madding brilliance",
                "1Q84 is a dystopian novel written by Japanese writer Haruki Murakami, first published in three volumes in Japan in 2009–10. It covers a fictionalized year of 1984 in parallel with a \"real\" one. The novel is a story of how a woman named Aomame begins to notice strange changes occurring in the world. /n 1Q84 is a dystopian novel written by Japanese writer Haruki Murakami, first published in three volumes in Japan in 2009–10. It covers a fictionalized year of 1984 in parallel with a \"real\" one. The novel is a story of how a woman named Aomame begins to notice strange changes occurring in the world.");
        setData(book);

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