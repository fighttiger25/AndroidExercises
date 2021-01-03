package com.example.uibasicssection3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBoxHarry, checkBoxMatrix, checkBoxJocker;
    private RadioGroup rgMaritalStatus;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxHarry = findViewById(R.id.checkboxHarry);
        checkBoxMatrix = findViewById(R.id.checkboxMatrix);
        checkBoxJocker = findViewById(R.id.checkboxJoker);

        progressBar = findViewById(R.id.progressBar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10; i++) {
                    progressBar.incrementProgressBy(10);
                    SystemClock.sleep(500);
                }
            }
        });
        thread.start();

        rgMaritalStatus = findViewById(R.id.rgMaritialStat);
        int checkedButton = rgMaritalStatus.getCheckedRadioButtonId();
        switch (checkedButton){
            case R.id.rbInRel:
                Toast.makeText(MainActivity.this, "in relationship", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.rbMarried:
                Toast.makeText(MainActivity.this, "married", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbMSingle:
                Toast.makeText(MainActivity.this, "single", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        rgMaritalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbInRel:
                        Toast.makeText(MainActivity.this, "in relationship", Toast.LENGTH_SHORT).show();
                        // progressBar.setVisibility(View.GONE);
                        break;
                    case  R.id.rbMarried:
                        Toast.makeText(MainActivity.this, "married", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbMSingle:
                        Toast.makeText(MainActivity.this, "single", Toast.LENGTH_SHORT).show();
                        // progressBar.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;

                }
            }
        });

        checkBoxHarry.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(MainActivity.this, "You have watched Harry Potter", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "You need to watch Harry Potter", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}