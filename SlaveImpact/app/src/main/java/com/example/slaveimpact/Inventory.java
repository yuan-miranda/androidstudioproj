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

        Object[][] chData = (Object[][]) getIntent().getSerializableExtra("chData");
        Intent resultIntent = new Intent();

        Button backBtn = findViewById(R.id.invBackBtn);
        ImageButton[] chSlots = new ImageButton[36];

        for (int i = 0; i < 36; i++) {
            // set id.
            int resId = getResources().getIdentifier("invChSlot" + (i + 1), "id", getPackageName());
            chSlots[i] = findViewById(resId);

            // set avatars
            chSlots[i].setImageResource(getResources().getIdentifier(String.valueOf(chData[i][4]), "drawable", getPackageName()));

            // set value of each slot.
            int finalI = i;
            chSlots[i].setOnClickListener(v -> HeroSelectPopup.startPopup(this, chData, finalI, () -> {
                chSlots[finalI].setImageResource(getResources().getIdentifier(String.valueOf(chData[finalI][4]), "drawable", getPackageName()));
                resultIntent.putExtra("chData", chData);
                setResult(RESULT_OK, resultIntent);
            }));
        }
        backBtn.setOnClickListener(v -> finish());
    }
}
