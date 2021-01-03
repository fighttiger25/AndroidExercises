package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView contactsRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsRecView = findViewById(R.id.contactsRecView);
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Margot Robbie", "margot@gmail.com", "https://upload.wikimedia.org/wikipedia/commons/9/98/Margot_Robbie_at_Somerset_House_in_2013_%28cropped%29.jpg"));
        contacts.add(new Contact("Cillian Murphy", "cillian@gmail.com", "https://static.independent.co.uk/s3fs-public/thumbnails/image/2019/08/08/09/gettyimages-818534548.jpg"));
        contacts.add(new Contact("Cristian Bale", "cristian@gmail.com", "https://www.nme.com/wp-content/uploads/2020/03/Christian-Bale.jpg"));

        ContactsRecViewAdapter adapter = new ContactsRecViewAdapter(this);
        adapter.setContacts(contacts);

        contactsRecView.setAdapter(adapter);
        // contactsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        contactsRecView.setLayoutManager(new GridLayoutManager(this, 2));
    }

}