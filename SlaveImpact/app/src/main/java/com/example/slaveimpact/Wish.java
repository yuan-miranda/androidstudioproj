package com.example.slaveimpact;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Wish extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wish);

        Object[][] chData = (Object[][]) getIntent().getSerializableExtra("chData");
        Button backBtn = findViewById(R.id.wishBackBtn);
        Button wishBtn = findViewById(R.id.wishWishBtn);
        TextView primogemDisplay = findViewById(R.id.wishPrimogemDisplay);

        ImageView wishChSlot1 = findViewById(R.id.wishChSlot1);
        ImageView wishChSlot2 = findViewById(R.id.wishChSlot2);
        ImageView wishChSlot3 = findViewById(R.id.wishChSlot3);
        ImageView wishChSlot4 = findViewById(R.id.wishChSlot4);

        wishChSlot1.setImageResource(getResources().getIdentifier(String.valueOf(chData[0][4]), "drawable", getPackageName()));
        wishChSlot2.setImageResource(getResources().getIdentifier(String.valueOf(chData[1][4]), "drawable", getPackageName()));
        wishChSlot3.setImageResource(getResources().getIdentifier(String.valueOf(chData[2][4]), "drawable", getPackageName()));
        wishChSlot4.setImageResource(getResources().getIdentifier(String.valueOf(chData[3][4]), "drawable", getPackageName()));

        primogemDisplay.setText(String.valueOf(GlobalValues.primogems));

        backBtn.setOnClickListener(v -> finish());

        wishBtn.setOnClickListener(v -> {
        });


    }
}
