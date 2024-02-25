package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtCount = findViewById(R.id.txtCount);
        TextView txtPopup = findViewById(R.id.txtPopup);
        Button btnTap = findViewById(R.id.btnTap);
        Button btnReset = findViewById(R.id.btnReset);

        // init SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("valueCache", Context.MODE_PRIVATE);

        // load the value
        count = sharedPreferences.getInt("valueCache", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        txtCount.setText(String.valueOf(count));

        btnTap.setOnClickListener(v -> {
            count++;

            txtCount.setText(String.valueOf(count));
            if (count == 69) {
                txtPopup.setVisibility(View.VISIBLE);
                new Handler().postDelayed(() -> txtPopup.setVisibility(View.INVISIBLE), 5000);
            }

            // save the value
            editor.putInt("valueCache", count);
            editor.apply();
        });

        btnReset.setOnClickListener(v -> {
            count = 0;
            txtCount.setText(String.valueOf(count));

            // save the value
            editor.putInt("valueCache", count);
            editor.apply();
        });
    }
}