package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView recViewbooks;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        recViewbooks = findViewById(R.id.recViewBooks);
        adapter = new BookRecViewAdapter(this, "allBooks");
        adapter.setBooks(Utils.getInstance().getAllBooks());

        recViewbooks.setAdapter(adapter);
        // recViewbooks.setLayoutManager(new GridLayoutManager(this, 2));
        recViewbooks.setLayoutManager(new LinearLayoutManager(this));

    }
}