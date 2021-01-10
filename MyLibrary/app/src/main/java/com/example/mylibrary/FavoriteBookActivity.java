package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class FavoriteBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_book);

        RecyclerView recyclerView = findViewById(R.id.bookRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this, "favoriteBooks", new UpdateCallBack() {
            @Override
            public void updateView(AppCompatActivity activity) {
                RecyclerView recyclerView = findViewById(R.id.bookRecView);
                ArrayList<Book> books = Utils.getInstance(activity).getFavoriteBooks();
                BookRecViewAdapter adapter = (BookRecViewAdapter) recyclerView.getAdapter();
                adapter.setBooks(books);
                recyclerView.setAdapter(adapter);
            }
        });


        ArrayList<Book> favoriteBooks = Utils.getInstance(this).getFavoriteBooks();
        adapter.setBooks(favoriteBooks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
