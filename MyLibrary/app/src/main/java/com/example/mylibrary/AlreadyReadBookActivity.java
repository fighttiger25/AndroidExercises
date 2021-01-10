package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class AlreadyReadBookActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_book);

        recyclerView = findViewById(R.id.bookRecView);
        UpdateCallBack updateCallBack = new UpdateCallBack() {
            @Override
            public void updateView(AppCompatActivity activity) {
                RecyclerView recyclerView = findViewById(R.id.bookRecView);
                ArrayList<Book> books = Utils.getInstance(activity).getAlreadyReadBooks();
                BookRecViewAdapter adapter = (BookRecViewAdapter) recyclerView.getAdapter();
                adapter.setBooks(books);
                recyclerView.setAdapter(adapter);
            }
        };
        BookRecViewAdapter adapter = new BookRecViewAdapter(this,"alreadyRead", updateCallBack);


        ArrayList<Book> alreadyReadBooks = Utils.getInstance(this).getAlreadyReadBooks();
        adapter.setBooks(alreadyReadBooks);
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