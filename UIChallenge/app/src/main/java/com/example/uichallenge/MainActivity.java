package com.example.uichallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.logging.Logger;


public class MainActivity extends AppCompatActivity {

    private final static Logger LOG = Logger.getLogger(MainActivity.class.getName());

    private EditText name, email, password, rePassword;
    private RadioGroup genderRgroup;
    private Spinner spinnerCountry;
    private CheckBox agree;
    private Button registerBtn;
    private ConstraintLayout parent;

    String checkedGenre = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.editTxtName);
        email = findViewById(R.id.editTxtEmail);
        password = findViewById(R.id.editTxtPassword);
        rePassword = findViewById(R.id.editTxtRepassword);
        genderRgroup = findViewById(R.id.rgGenre);
        spinnerCountry = findViewById(R.id.spinnerCountry);
        agree = findViewById(R.id.checkBox);
        registerBtn = findViewById(R.id.registerBtn);
        parent = findViewById(R.id.parent);

        genderRgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                    @Override
                                                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                        RadioButton btn = group.findViewById(checkedId);
                                                        //Toast.makeText(MainActivity.this, btn.getText().toString() + " Checked", Toast.LENGTH_SHORT).show();
                                                        checkedGenre = btn.getText().toString();
                                                    }
                                                }
        );

        registerBtn.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               if (!agree.isChecked()) {
                                                   Toast.makeText(MainActivity.this, "Please check agreement", Toast.LENGTH_SHORT).show();
                                                   return;
                                               }
                                               if (!password.getText().toString().equals(rePassword.getText().toString()) || password.getText().toString().length() == 0) {
                                                   Toast.makeText(MainActivity.this, "Entered passwords are not consistent", Toast.LENGTH_SHORT).show();
                                                   return;
                                               }

                                               if (name.getText() != null && email.getText() != null && password.getText() != null && checkedGenre != null && spinnerCountry.getSelectedItem() != null) {
                                                   Snackbar.make(parent, name.getText().toString() + "\n" + email.getText().toString(), Snackbar.LENGTH_INDEFINITE).setAction("Dismiss", new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {

                                                       }
                                                   }).show();
                                               } else {
                                                   Toast.makeText(MainActivity.this, "Some fields are missed ", Toast.LENGTH_SHORT).show();
                                                   return;
                                               }
                                           }
                                       }
        );
    }
}