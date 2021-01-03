package com.example.challengetest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnRegisterClick(View view){
        EditText editTextFirstName = findViewById(R.id.editTxtFirstName);
        TextView textViewFirstName = findViewById(R.id.textViewFirstName);
        textViewFirstName.setText("First Name: " + editTextFirstName.getText().toString());

        EditText editTextLastName = findViewById(R.id.editTxtLastName);
        TextView textViewLastName = findViewById(R.id.textViewLastName);
        textViewLastName.setText("Last Name: " + editTextLastName.getText().toString());

        EditText editTextMail = findViewById(R.id.editTextTextEmailAddress);
        TextView textViewEmail = findViewById(R.id.textViewEmail);
        textViewEmail.setText("Email: " + editTextMail.getText().toString());
    }
}