package com.example.test;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.button);

        // call the popup dialog via button
        btn.setOnClickListener(v -> {
            PopupDialogue.showPopup(this, "Test Popup", "Your mom is hot");
        });
    }
}