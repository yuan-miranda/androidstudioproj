package com.example.slaveimpact;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainScreen extends AppCompatActivity {
    private int primogems = 0;
    private Object[][] chData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        Button tapBtn = findViewById(R.id.tapBtn);
        Button invBtn = findViewById(R.id.invBtn);
        Button wishBtn = findViewById(R.id.wishBtn);

        TextView primogemDisplay = findViewById(R.id.primogemDisplay);

        // TODO: Make setEnable for each slots, hide also the lv display.

        primogems = LoadData.getInstance().loadPrimogems(this);
        chData = LoadData.getInstance().loadCharacterData(this);
        chData = LoadData.getInstance().sortChData(chData);
        LoadData.getInstance().saveCharacterData(this, chData);
        GlobalValues.setMainScreenInstance(this);

        ImageButton chSlot1 = findViewById(R.id.chSlot1);
        ImageButton chSlot2 = findViewById(R.id.chSlot2);
        ImageButton chSlot3 = findViewById(R.id.chSLot3);
        ImageButton chSlot4 = findViewById(R.id.chSlot4);

        TextView chLvSlot1 = findViewById(R.id.chLvSlot1);
        TextView chLvSlot2 = findViewById(R.id.chLvSlot2);
        TextView chLvSlot3 = findViewById(R.id.chLvSlot3);
        TextView chLvSlot4 = findViewById(R.id.chLvSlot4);

        chLvSlot1.setText("lv. " + chData[0][1]);
        chLvSlot2.setText("lv. " + chData[1][1]);
        chLvSlot3.setText("lv. " + chData[2][1]);
        chLvSlot4.setText("lv. " + chData[3][1]);

        chSlot1.setImageResource(getResources().getIdentifier(String.valueOf(chData[0][4]), "drawable", getPackageName()));
        chSlot2.setImageResource(getResources().getIdentifier(String.valueOf(chData[1][4]), "drawable", getPackageName()));
        chSlot3.setImageResource(getResources().getIdentifier(String.valueOf(chData[2][4]), "drawable", getPackageName()));
        chSlot4.setImageResource(getResources().getIdentifier(String.valueOf(chData[3][4]), "drawable", getPackageName()));

        chSlot1.setOnClickListener(v -> HeroSelectPopup.startPopup(this, chData, 0, () -> {
            chSlot1.setImageResource(getResources().getIdentifier(String.valueOf(chData[0][4]), "drawable", getPackageName()));
            chLvSlot1.setText("lv. " + chData[0][1]);
        }));
        chSlot2.setOnClickListener(v -> HeroSelectPopup.startPopup(this, chData, 1, () -> {
            chSlot2.setImageResource(getResources().getIdentifier(String.valueOf(chData[1][4]), "drawable", getPackageName()));
            chLvSlot2.setText("lv. " + chData[1][1]);
        }));
        chSlot3.setOnClickListener(v -> HeroSelectPopup.startPopup(this, chData, 2, () -> {
            chSlot3.setImageResource(getResources().getIdentifier(String.valueOf(chData[2][4]), "drawable", getPackageName()));
            chLvSlot3.setText("lv. " + chData[2][1]);
        }));
        chSlot4.setOnClickListener(v -> HeroSelectPopup.startPopup(this, chData, 3, () -> {
            chSlot4.setImageResource(getResources().getIdentifier(String.valueOf(chData[3][4]), "drawable", getPackageName()));
            chLvSlot4.setText("lv. " + chData[3][1]);
        }));

        primogemDisplay.setText(String.valueOf(primogems));

        tapBtn.setOnClickListener(v -> {
            primogems++;
            primogemDisplay.setText(String.valueOf(primogems));
        });

        invBtn.setOnClickListener(v -> {
            // TODO: Update the main screen slot when selling on inventory. Dismiss Callback?
            Intent intent = new Intent(MainScreen.this, Inventory.class);
            intent.putExtra("chData", chData);
            startActivity(intent);
        });
        wishBtn.setOnClickListener(v -> {
            GlobalValues.primogems = primogems;
            Intent intent = new Intent(MainScreen.this, Wish.class);
            intent.putExtra("chData", chData);
            startActivity(intent);
        });
    }
    public void updateCharacterData(Object[] newData, int index) {
        chData[index] = newData;
        LoadData.getInstance().saveCharacterData(this, chData);
    }
}
