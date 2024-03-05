package com.example.slaveimpact;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Inventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory);

        Button backBtn = findViewById(R.id.invBackBtn);

        backBtn.setOnClickListener(v -> {
            finish();
        });

        ImageButton[] chSlots = new ImageButton[36];

        // set id.
        for (int i = 0; i < 36; i++) {
            int resId = getResources().getIdentifier("invChSlot" + (i + 1), "id", getPackageName());
            chSlots[i] = findViewById(resId);
        }

        // set value of each slot.
        for (int i = 0; i < 36; i++) {
            int finalI = i;
            chSlots[i].setOnClickListener(v -> {
                GlobalValues.name = GlobalValues.characterData[finalI][0];
                GlobalValues.level = GlobalValues.characterData[finalI][1];
                HeroSelectPopup.startPopup(this);
            });
        }

        // TODO: Set avatar?
        for (int i = 0; i < 36; i++) {
            chSlots[i].setImageResource(getResources().getIdentifier(String.valueOf(GlobalValues.avatar), "drawable", getPackageName()));
        }
    }
}
