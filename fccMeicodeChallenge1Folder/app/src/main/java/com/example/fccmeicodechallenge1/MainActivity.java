// https://youtu.be/fis26HvvDII?si=-s-jzFuJprzA8Y6D&t=59m20s

package com.example.fccmeicodechallenge1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtTxtFirstName = findViewById(R.id.edtTxtFirstName);
        EditText edtTxtLastName = findViewById(R.id.edtTxtLastName);
        EditText edtTxtEmail = findViewById(R.id.edtTxtEmail);

        Button btnRegister = findViewById(R.id.btnRegister);

        TextView txtFirstName = findViewById(R.id.txtFirstName);
        TextView txtLastName = findViewById(R.id.txtLastName);
        TextView txtEmail = findViewById(R.id.txtEmail);

        btnRegister.setOnClickListener(v -> {
            txtFirstName.setText("First Name: " + edtTxtEmail.getText().toString());
            txtLastName.setText("Last Name: " + edtTxtLastName.getText().toString());
            txtEmail.setText("Email: " + edtTxtEmail.getText().toString());
        });

    }
}