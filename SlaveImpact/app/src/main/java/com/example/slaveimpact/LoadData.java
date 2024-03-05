package com.example.slaveimpact;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoadData {
    private static LoadData instance;

    private LoadData(){

    }
    public static LoadData getInstance() {
        if (instance == null) {
            instance = new LoadData();
        }
        return instance;
    }

    void saveCharacterData(Context context, Object[][] charactersData) {
        try {
            FileOutputStream fos = context.openFileOutput("characters.dat", Context.MODE_PRIVATE);
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

    // TODO: Fix the blank crash button problem when clicking avatars.


    Object[][] loadCharacterData(Context context) {
        try {
            // try to load the data from the storage.
            FileInputStream fis = context.openFileInput("characters.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            GlobalValues.characterData = (Object[][]) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            // if there's no data existing, create one.
            GlobalValues.characterData = new Object[36][5];
            // generate all the character slots.
            for (int i = 0; i < 36; i++) {
                GlobalValues.characterData[i] = new Object[]{"", 0, 0.0, false, "raiden_shogun_icon"};
            }
            // save the data just in case something sus happens.
            saveCharacterData(context, GlobalValues.characterData);
        }
        // return the data
        return GlobalValues.characterData;
    }

    int loadPrimogems(Context context) {
        int primogems = 0;
        try {
            FileInputStream fis = context.openFileInput("primogems.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            primogems = (int) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            savePrimogems(context, primogems);
        }
        return primogems;
    }
}
