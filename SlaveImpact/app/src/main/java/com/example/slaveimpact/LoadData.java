package com.example.slaveimpact;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class LoadData {
    private static LoadData instance;

    private LoadData() {

    }

    public static LoadData getInstance() {
        if (instance == null) {
            instance = new LoadData();
        }
        return instance;
    }

    Object[][] sortChData(Object[][] chData) {
        Arrays.sort(chData, (Object[] o1, Object[] o2) -> {
            Integer num1 = (Integer) o1[3];
            Integer num2 = (Integer) o2[3];
            if (num1 == 0 && num2 == 0) return 0;
            if (num1 == 0) return 1;
            if (num2 == 0) return -1;
            return num1.compareTo(num2);
        });
        return chData;
    }

    void saveCharacterData(Object[][] charactersData) {
        try (FileOutputStream fos = new FileOutputStream("characters.dat", false);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(charactersData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void savePrimogems(Context context, int value) {
        try (FileOutputStream fos = context.openFileOutput("primogems.dat", Context.MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Object[][] loadCharacterData(Context context) {
        Object[][] tempData;
        try (FileInputStream fis = context.openFileInput("characters.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            tempData = (Object[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            tempData = new Object[36][5];
            // generate all the character slots.
            for (int i = 0; i < 36; i++) {
                tempData[i] = new Object[]{"name_here", 0, 0.0, 0, "xiao_icon"};
            }
            // save the data just in case something sus happens.
            saveCharacterData(tempData);
        }
        // return the data
        return Arrays.copyOf(tempData, tempData.length);
    }

    int loadPrimogems(Context context) {
        int primogems = 0;
        try (FileInputStream fis = context.openFileInput("primogems.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            primogems = (int) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            savePrimogems(context, primogems);
        }
        return primogems;
    }
}
