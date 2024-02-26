package com.example.getinput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSend = findViewById(R.id.btnSend);
        TextInputEditText txtInputEditText = findViewById(R.id.txtInputEditText);
        TextView txtView = findViewById(R.id.txtView);

        btnSend.setOnClickListener(v -> {
            txtView.setText(txtInputEditText.getText().toString());
        });

    }
}