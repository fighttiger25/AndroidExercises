package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {

    private RecyclerView recViewbooks;
    private BookRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

//        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        recViewbooks = findViewById(R.id.recViewBooks);
        adapter = new BookRecViewAdapter(this, "allBooks", new UpdateCallBack() {
            @Override
            public void updateView(AppCompatActivity activity) {
            }
        });
        adapter.setBooks(Utils.getInstance(this).getAllBooks());

        recViewbooks.setAdapter(adapter);
        // recViewbooks.setLayoutManager(new GridLayoutManager(this, 2));
        recViewbooks.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // display up button

    }

//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
//    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}