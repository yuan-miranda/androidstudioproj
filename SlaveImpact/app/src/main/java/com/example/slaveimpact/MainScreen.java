package com.example.slaveimpact;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        ImageButton chSlot1 = findViewById(R.id.chSlot1);
        ImageButton chSlot2 = findViewById(R.id.chSlot2);
        ImageButton chSlot3 = findViewById(R.id.chSLot3);
        ImageButton chSlot4 = findViewById(R.id.chSlot4);

        TextView chLvSlot1 = findViewById(R.id.chLvSlot1);
        TextView chLvSlot2 = findViewById(R.id.chLvSlot2);
        TextView chLvSlot3 = findViewById(R.id.chLvSlot3);
        TextView chLvSlot4 = findViewById(R.id.chLvSlot4);

        primogems = LoadData.getInstance().loadPrimogems(this);
        chData = LoadData.getInstance().loadCharacterData(this);

        primogemDisplay.setText(String.valueOf(primogems));

        GlobalValues.avatar = chData[0][4];
        chSlot1.setImageResource(getResources().getIdentifier(String.valueOf(GlobalValues.avatar), "drawable", getPackageName()));
        chSlot2.setImageResource(getResources().getIdentifier(String.valueOf("yelan_icon"), "drawable", getPackageName()));
        chSlot3.setImageResource(getResources().getIdentifier(String.valueOf("nahida_icon"), "drawable", getPackageName()));
        chSlot4.setImageResource(getResources().getIdentifier(String.valueOf("xiao_icon"), "drawable", getPackageName()));

        tapBtn.setOnClickListener(v -> {
            primogems++;
            primogemDisplay.setText(String.valueOf(primogems));
        });

        invBtn.setOnClickListener(v -> {
            GlobalValues.avatar = chData[0][4];
            Intent intent = new Intent(MainScreen.this, Inventory.class);
            startActivity(intent);
        });
        wishBtn.setOnClickListener(v -> {
            GlobalValues.primogems = primogems;
            GlobalValues.avatar = chData[0][4];
            Intent intent = new Intent(MainScreen.this, Wish.class);
            startActivity(intent);
        });

        // TODO: finish the loading of character each in inventory?
        chSlot1.setOnClickListener(v -> {
            GlobalValues.name = chData[0][0];
            GlobalValues.level = chData[0][1];
            GlobalValues.avatar = chData[0][4];
            HeroSelectPopup.startPopup(this);

        });
        chSlot2.setOnClickListener(v -> {
            GlobalValues.name = chData[1][0];
            GlobalValues.level = chData[1][1];
//            GlobalValues.avatar = chData[1][4];
            GlobalValues.avatar = "yelan_icon";
            HeroSelectPopup.startPopup(this);
        });
        chSlot3.setOnClickListener(v -> {
            GlobalValues.name = chData[2][0];
            GlobalValues.level = chData[2][1];
//            GlobalValues.avatar = chData[2][4];
            GlobalValues.avatar = "nahida_icon";
            HeroSelectPopup.startPopup(this);
        });
        chSlot4.setOnClickListener(v -> {
            GlobalValues.name = chData[3][0];
            GlobalValues.level = chData[3][1];
//            GlobalValues.avatar = chData[3][4];
            GlobalValues.avatar = "xiao_icon";
            HeroSelectPopup.startPopup(this);
        });

        chLvSlot1.setText("lv. " + String.valueOf(chData[0][1]));
        chLvSlot2.setText("lv. " + String.valueOf(chData[1][1]));
        chLvSlot3.setText("lv. " + String.valueOf(chData[2][1]));
        chLvSlot4.setText("lv. " + String.valueOf(chData[3][1]));
    }
}
