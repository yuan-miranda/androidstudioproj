package com.example.slaveimpact;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Wish extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish);

        Button backBtn = findViewById(R.id.wishBackBtn);
        Button wishBtn = findViewById(R.id.wishWishBtn);
        TextView primogemDisplay = findViewById(R.id.wishPrimogemDisplay);

        ImageView wishChSlot1 = findViewById(R.id.wishChSlot1);
        ImageView wishChSlot2 = findViewById(R.id.wishChSlot2);
        ImageView wishChSlot3 = findViewById(R.id.wishChSlot3);
        ImageView wishChSlot4 = findViewById(R.id.wishChSlot4);

        wishChSlot1.setImageResource(getResources().getIdentifier(String.valueOf(GlobalValues.avatar), "drawable", getPackageName()));
        wishChSlot2.setImageResource(getResources().getIdentifier(String.valueOf(GlobalValues.avatar), "drawable", getPackageName()));
        wishChSlot3.setImageResource(getResources().getIdentifier(String.valueOf(GlobalValues.avatar), "drawable", getPackageName()));
        wishChSlot4.setImageResource(getResources().getIdentifier(String.valueOf(GlobalValues.avatar), "drawable", getPackageName()));

        primogemDisplay.setText(String.valueOf(GlobalValues.primogems));

        backBtn.setOnClickListener(v -> {
            finish();
        });

        wishBtn.setOnClickListener(v -> {
            // do something
        });


    }
}
