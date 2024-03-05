package com.example.slaveimpact;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Ingame extends AppCompatActivity {
    private int primogems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingame);

        // initialize values.
        Button tapBtn = findViewById(R.id.tapButton);
        Button heroBtn = findViewById(R.id.heroBtn);
        Button wishBtn = findViewById(R.id.wishBtn);

        TextView primogemDisplay = findViewById(R.id.primogemDisplay);
        ImageButton[] chSlot = new ImageButton[6];
        TextView[] chLevelSlot = new TextView[6];

        for (int i = 0; i < 4; i++) {
            int chResId = getResources().getIdentifier("chSlot" + (i + 1), "id", getPackageName());
            int chLvlResId = getResources().getIdentifier("chLvlSlot" + (i + 1), "id", getPackageName());

            chSlot[i] = findViewById(chResId);
            chSlot[i].setImageResource(R.drawable.duwud);
            chSlot[i].setVisibility(View.GONE);

            chLevelSlot[i] = findViewById(chLvlResId);
            chLevelSlot[i].setVisibility(View.GONE);

        }

        // load the data.
        primogems = loadPrimogems(this);
        Object[][] charactersData = loadCharactersData(this);

        // change the Tap button's appearance based on system theme.
        boolean isDarkMode = ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES);
        if (isDarkMode) {
            tapBtn.setBackgroundResource(R.drawable.roundbutton_dark);
        }
        else {
            tapBtn.setBackgroundResource(R.drawable.roundbutton_light);
        }

        primogemDisplay.setText(String.valueOf(primogems));

        chSlot[0].setVisibility(View.VISIBLE);
        chLevelSlot[0].setVisibility(View.VISIBLE);

        tapBtn.setOnClickListener(v -> {
            primogems++;
            primogemDisplay.setText(String.valueOf(primogems));
        });
        heroBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Ingame.this, Hero.class);
            startActivity(intent);
        });
        wishBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Ingame.this, Wish.class);
            startActivity(intent);
        });

        // change the logic so this will open the necessary popup slot
        // TODO: make name
        chSlot[0].setOnClickListener(v -> {
            GlobalValue.name = charactersData[0][0];
            GlobalValue.level = charactersData[0][1];
            HeroPopup.showHeroPopup(this);
        });
        chSlot[1].setOnClickListener(v -> {
            GlobalValue.name = charactersData[1][0];
            GlobalValue.level = charactersData[1][1];
            HeroPopup.showHeroPopup(this);
        });
        chSlot[2].setOnClickListener(v -> {
            GlobalValue.name = charactersData[2][0];
            GlobalValue.level = charactersData[2][1];
            HeroPopup.showHeroPopup(this);
        });
        chSlot[3].setOnClickListener(v -> {
            GlobalValue.name = charactersData[3][0];
            GlobalValue.level = charactersData[3][1];
            HeroPopup.showHeroPopup(this);
        });


    }

    void saveCharactersData(Context context, Object[][] charactersData) {
        try {
            FileOutputStream fos = context.openFileOutput("charactersdata.dat", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(charactersData);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void savePrimogems(Context context, int value) {
        try {
            FileOutputStream fos = context.openFileOutput("primogems.dat", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(value);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Object[][] loadCharactersData(Context context) {
        Object[][] charactersData = {
                {"Aether", 0, 0.0, true},
                {"", 0, 0.0, false},
                {"", 0, 0.0, false},
                {"", 0, 0.0, false}};
        try {
            FileInputStream fis = context.openFileInput("characters.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            charactersData = (Object[][]) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            saveCharactersData(context, charactersData);
        }
        return charactersData;
    }

    int loadPrimogems(Context context) {
        int primogems = 0;
        try {
            FileInputStream fis = context.openFileInput("characters.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            primogems = (int) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            savePrimogems(context, primogems);
        }
        return primogems;
    }

}
