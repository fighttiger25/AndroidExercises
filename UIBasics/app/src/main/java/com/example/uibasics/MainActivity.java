package com.example.uibasics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewHello;
    EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnHello = findViewById(R.id.btnHello);
        textViewHello = findViewById(R.id.txtHello);
        editTextName = findViewById(R.id.edtTxtName);

        editTextName.setOnClickListener(this);
        btnHello.setOnClickListener(this);
        btnHello.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.this, "Long Pressed", Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    // every Android activity is a context
    // every UI element is a view
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnHello:
                System.out.println("Hello Again");
                textViewHello.setText("Hello " + editTextName.getText().toString());
                // Toast.makeText(this, "Hello Button Pressed", Toast.LENGTH_SHORT).show();
                break;
            case R.id.edtTxtName:
                Toast.makeText(this, "Attempting to type something ", Toast.LENGTH_SHORT).show();
            default:
                break;
        }
    }
}