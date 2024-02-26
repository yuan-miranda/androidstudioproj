package com.example.stackinginput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.scrollView);
        linearLayout = findViewById(R.id.linearLayout);
        TextInputEditText txtInputEditText = findViewById(R.id.txtInputEditTxt);
        Button btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(v -> {
            String input = txtInputEditText.getText().toString();
            addTextToStack(input);
        });
    }

    void addTextToStack(String str) {
        TextView txtView = new TextView(this);
        txtView.setText(str);
        linearLayout.addView(txtView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}