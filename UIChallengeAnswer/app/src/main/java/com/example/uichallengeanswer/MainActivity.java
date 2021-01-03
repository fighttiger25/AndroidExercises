package com.example.uichallengeanswer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText edtTxtName, edtTxtEmail, edtTxtPassword, edtTxtPassRepaeat;
    private Button btnPickImage, btnRegister;
    private TextView txtWarnName, txtWarnEmail, txtWarnPassword, txtWarnPassRepeat;
    private Spinner spinnerCountries;
    private RadioGroup rgGender;
    private CheckBox agreementCheck;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViews();

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Yet to talk about", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRegister();
            }


        });
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: Started");

        if (validateData()){
            if (agreementCheck.isChecked()){
                showSnackBar();
            }
            else{
                Toast.makeText(this, "Please check agreement", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: started");
        txtWarnPassRepeat.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        txtWarnName.setVisibility(View.GONE);
        txtWarnEmail.setVisibility(View.GONE);

        Snackbar.make(parent, "User Registered", Snackbar.LENGTH_SHORT).setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTxtName.setText("");
                edtTxtEmail.setText("");
                edtTxtPassword.setText("");
                edtTxtPassRepaeat.setText("");
            }
        }).show();
    }

    private Boolean validateData() {
        Log.d(TAG, "validateData: started");
        if (edtTxtName.getText().toString().equals("")) {
            txtWarnName.setVisibility(View.VISIBLE);
            return false;
        }
        else {
            txtWarnName.setVisibility(View.GONE);
        }
        if (edtTxtEmail.getText().toString().equals("")){
            txtWarnEmail.setVisibility(View.VISIBLE);
            return false;
        }
        else {
            txtWarnEmail.setVisibility(View.GONE);
        }
        if (edtTxtPassword.getText().toString().equals("")){
            txtWarnPassword.setVisibility(View.VISIBLE);
            return false;
        }
        else {
            txtWarnPassword.setVisibility(View.GONE);
        }
        if (edtTxtPassRepaeat.getText().toString().equals("")){
            txtWarnPassRepeat.setVisibility(View.VISIBLE);
            return false;
        }
        else {
            txtWarnPassRepeat.setVisibility(View.GONE);
        }
        if (!edtTxtPassRepaeat.getText().toString().equals(edtTxtPassword.getText().toString())){
            txtWarnPassword.setText("Password Inconsistant");
            txtWarnPassword.setVisibility(View.VISIBLE);
            return false;
        }
        else {
            txtWarnPassword.setText("Missed");
            txtWarnPassword.setVisibility(View.GONE);
        }
        return true;
    }



    private void initViews() {
        Log.d(TAG, "initViews: started");

        edtTxtName = findViewById(R.id.edtTxtName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtPassRepaeat = findViewById(R.id.edtTxtRepassword);

        btnPickImage = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);

        txtWarnName = findViewById(R.id.txtWarnName);
        txtWarnEmail = findViewById(R.id.txtWarnEmail);
        txtWarnPassword = findViewById(R.id.txtWarnPassword);
        txtWarnPassRepeat = findViewById(R.id.txtWarnRepassword);

        spinnerCountries = findViewById(R.id.spinnerCountry);

        rgGender = findViewById(R.id.rgGender);
        agreementCheck = findViewById(R.id.agreementCheck);

        parent = findViewById(R.id.parent);

    }
}
