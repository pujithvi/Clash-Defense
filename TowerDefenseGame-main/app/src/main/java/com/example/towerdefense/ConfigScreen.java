package com.example.towerdefense;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ConfigScreen extends AppCompatActivity {
    private Spinner difficulty;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_config_screen);

        submit = findViewById(R.id.submitButton);
        difficulty = findViewById(R.id.difficultyInput);
        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Easy");
        spinnerArray.add("Medium");
        spinnerArray.add("Hard");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, spinnerArray);
        difficulty.setAdapter(adapter);

        EditText text = findViewById(R.id.nameInput);

        TextView warning = findViewById(R.id.warningMessage);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = difficulty.getSelectedItem().toString();
                String nameInputted = text.getText().toString();
                if (nameInputted.trim().isEmpty()) {
                    warning.setText("Please Enter a Valid Name!");
                } else {
                    if (selected.equals("Easy")) {
                        openEasyScreen(nameInputted, selected.toLowerCase());
                    } else if (selected.equals("Medium")) {
                        openMediumScreen(nameInputted, selected.toLowerCase());
                    } else {
                        openHardScreen(nameInputted, selected.toLowerCase());
                    }
                }
            }
        });
    }

    public void openEasyScreen(String nameInputted, String difficulty) {
        Intent intent1 = new Intent(this, GameScreen.class);
        intent1.putExtra("nameInputted", nameInputted);
        intent1.putExtra("difficulty", difficulty);
        startActivity(intent1);
    }

    public void openMediumScreen(String nameInputted, String difficulty) {
        Intent intent2 = new Intent(this, GameScreen.class);
        intent2.putExtra("nameInputted", nameInputted);
        intent2.putExtra("difficulty", difficulty);
        startActivity(intent2);
    }

    public void openHardScreen(String nameInputted, String difficulty) {
        Intent intent3 = new Intent(this, GameScreen.class);
        intent3.putExtra("nameInputted", nameInputted);
        intent3.putExtra("difficulty", difficulty);
        startActivity(intent3);
    }
}
