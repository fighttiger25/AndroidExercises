package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnShowAllBooks, btnCurrentlyReading, btnAlreadyRead, btnWishList, btnSeeYourFavorite, btnShowAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnShowAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooksActivity.class); // activity is context
                startActivity(intent);

            }
        });
    }

    private void initViews() {
        btnShowAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWishList = findViewById(R.id.btnWantToRead);
        btnSeeYourFavorite = findViewById(R.id.btnSeeFavorite);
        btnShowAbout = findViewById(R.id.btnAbout);
    }
}