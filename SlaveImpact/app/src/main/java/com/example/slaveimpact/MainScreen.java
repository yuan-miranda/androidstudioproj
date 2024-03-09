package com.example.slaveimpact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainScreen extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    ImageButton[] chSlots = new ImageButton[4];
    TextView[] chLvSlots = new TextView[4];
    ImageButton chSlot1;
    ImageButton chSlot2;
    ImageButton chSlot3;
    ImageButton chSlot4;
    TextView chLvSlot1;
    TextView chLvSlot2;
    TextView chLvSlot3;
    TextView chLvSlot4;
    private int primogems = 0;
    private Object[][] chData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        Intent inventoryIntent = new Intent(MainScreen.this, Inventory.class);
        Intent wishIntent = new Intent(MainScreen.this, Wish.class);
        primogems = LoadData.getInstance().loadPrimogems(this);
        chData = LoadData.getInstance().loadCharacterData(this);
        chData = LoadData.getInstance().sortChData(chData);

        LoadData.getInstance().saveCharacterData(this, chData);
        GlobalValues.setMainScreenInstance(this);

        Button tapBtn = findViewById(R.id.tapBtn);
        Button invBtn = findViewById(R.id.invBtn);
        Button wishBtn = findViewById(R.id.wishBtn);
        TextView primogemDisplay = findViewById(R.id.primogemDisplay);

//        doesnt work
//        for (int i = 0; i < 4; i++) {
//            int resIdSlot = getResources().getIdentifier("chSlot" + (i + 1), "id", getPackageName());
//            int resIdLvSlot = getResources().getIdentifier("chLvSlot" + (i + 1), "id", getPackageName());
//            chSlots[i] = findViewById(resIdSlot);
//            chLvSlots[i] = findViewById(resIdLvSlot);
//        }
//
//        for (int i = 4; i < 4; i++) {
//            int finalI = i;
//            chSlots[i].setOnClickListener(v -> HeroSelectPopup.startPopup(this, chData, finalI, () -> {
//                chSlots[finalI].setImageResource(getResources().getIdentifier(String.valueOf(chData[finalI][4]), "drawable", getPackageName()));
//                chLvSlots[finalI].setText("lv. " + chData[finalI][1]);
//            }));
//        }

        chSlot1 = findViewById(R.id.chSlot1);
        chSlot2 = findViewById(R.id.chSlot2);
        chSlot3 = findViewById(R.id.chSLot3);
        chSlot4 = findViewById(R.id.chSlot4);

        chLvSlot1 = findViewById(R.id.chLvSlot1);
        chLvSlot2 = findViewById(R.id.chLvSlot2);
        chLvSlot3 = findViewById(R.id.chLvSlot3);
        chLvSlot4 = findViewById(R.id.chLvSlot4);

        // TODO: Make setEnable for each slots, hide also the lv display.

        primogemDisplay.setText(String.valueOf(primogems));

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


        tapBtn.setOnClickListener(v -> {
            primogems++;
            primogemDisplay.setText(String.valueOf(primogems));
        });

        invBtn.setOnClickListener(v -> {
            // TODO: Update the main screen slot when selling on inventory. Dismiss Callback?
            inventoryIntent.putExtra("chData", chData);
            startActivityForResult(inventoryIntent, REQUEST_CODE);
        });
        wishBtn.setOnClickListener(v -> {
            GlobalValues.primogems = primogems;
            wishIntent.putExtra("chData", chData);
            startActivityForResult(wishIntent, REQUEST_CODE);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // save data when your not in game.
        LoadData.getInstance().saveCharacterData(this, chData);
        LoadData.getInstance().savePrimogems(this, primogems);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // update the MainScreen UI when exiting the Inventory.
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                chLvSlot1.setText("lv. " + chData[0][1]);
                chLvSlot2.setText("lv. " + chData[1][1]);
                chLvSlot3.setText("lv. " + chData[2][1]);
                chLvSlot4.setText("lv. " + chData[3][1]);

                chSlot1.setImageResource(getResources().getIdentifier(String.valueOf(chData[0][4]), "drawable", getPackageName()));
                chSlot2.setImageResource(getResources().getIdentifier(String.valueOf(chData[1][4]), "drawable", getPackageName()));
                chSlot3.setImageResource(getResources().getIdentifier(String.valueOf(chData[2][4]), "drawable", getPackageName()));
                chSlot4.setImageResource(getResources().getIdentifier(String.valueOf(chData[3][4]), "drawable", getPackageName()));
            }
        }
    }

    public void updateCharacterData(Object[] newData, int index) {
        // im not sure of making this static, as im still confuse of the whole Java's access modifier.
        // update the MainScreen's chData value and save it.
        chData[index] = newData;
        LoadData.getInstance().saveCharacterData(this, chData);
    }
}
