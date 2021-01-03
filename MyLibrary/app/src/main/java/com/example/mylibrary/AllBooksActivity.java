package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView recViewbooks;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        recViewbooks = findViewById(R.id.recViewBooks);
        adapter = new BookRecViewAdapter(this);

        recViewbooks.setAdapter(adapter);
        // recViewbooks.setLayoutManager(new GridLayoutManager(this, 2));
        recViewbooks.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1, "1Q84", "Haruki Murakami", 130, "https://images-na.ssl-images-amazon.com/images/I/81M0jxrDz5L.jpg", "A work of madding brilliance", "1Q84 is a dystopian novel written by Japanese writer Haruki Murakami, first published in three volumes in Japan in 2009–10. It covers a fictionalized year of 1984 in parallel with a \"real\" one. The novel is a story of how a woman named Aomame begins to notice strange changes occurring in the world."));
        books.add(new Book(2, "Effective Java", "Joshua Bloch", 150,
                "https://images-na.ssl-images-amazon.com/images/I/51wl8cINKYL.jpg", "A work of Java",
                "The Definitive Guide to Java Platform Best Practices–Updated for Java 7, 8, and 9 Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java."));
        adapter.setBooks(books);
    }
}