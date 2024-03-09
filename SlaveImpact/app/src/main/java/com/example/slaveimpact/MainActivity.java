package com.example.slaveimpact;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, MainScreen.class);

        Button playBtn = findViewById(R.id.playBtn);

        playBtn.setOnClickListener(v -> {
            startActivity(intent);
        });

    }
}