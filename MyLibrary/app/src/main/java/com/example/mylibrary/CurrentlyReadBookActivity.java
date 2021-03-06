package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class CurrentlyReadBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_read_book);

        UpdateCallBack updateCallBack = new UpdateCallBack() {
            @Override
            public void updateView(AppCompatActivity activity) {
                RecyclerView recyclerView = findViewById(R.id.bookRecView);
                ArrayList<Book> books = Utils.getInstance(activity).getCurrentlyReadingBooks();
                BookRecViewAdapter adapter = (BookRecViewAdapter) recyclerView.getAdapter();
                adapter.setBooks(books);
                recyclerView.setAdapter(adapter);
            }
        };

        RecyclerView recyclerView = findViewById(R.id.bookRecView);
        BookRecViewAdapter adapter = new BookRecViewAdapter(this, "currentlyRead", updateCallBack);
        ArrayList<Book> currentlyReadBooks = Utils.getInstance(this).getCurrentlyReadingBooks();
        adapter.setBooks(currentlyReadBooks);
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