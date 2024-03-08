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
        // sort characters that has the priority number of 1-4 at index 3.
        // non zero number means active character but max is 4.
        Arrays.sort(chData, (o1, o2) -> {
            Integer num1 = (Integer) o1[3];
            Integer num2 = (Integer) o2[3];
            if (num1 == 0 && num2 == 0) return 0;
            if (num1 == 0) return 1;
            if (num2 == 0) return -1;
            return num1.compareTo(num2);
        });
        // find the first occurrence of 0 in range of < 5.
        // 0 means inactive character.
        int highest = 0;
        for(int i = 0; i < 5; i++) {
            if ((int) chData[i][3] == 0) {
                System.out.println("Highest: " + i);
                highest = i;
                break;
            }
        }
        // sort the rest of the characters in alphabetical order.
        Arrays.sort(chData, highest, chData.length, (o1, o2) -> {
            String str1 = (String) o1[0];
            String str2 = (String) o2[0];
            return str1.compareTo(str2);
        });
        return chData;
    }

    void saveCharacterData(Context context, Object[][] charactersData) {
        try (FileOutputStream fos = context.openFileOutput("characters.dat", Context.MODE_PRIVATE);
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
            // default character
            tempData[0] = new Object[]{"Aether", 0, 0.0, 1, "aether_icon"};
            // generate all the character slots.
            for (int i = 1; i < 36; i++) {
                tempData[i] = new Object[]{"", 0, 0.0, 0, "blank"};
            }
            // save the data just in case something sus happens.
            saveCharacterData(context, tempData);
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
