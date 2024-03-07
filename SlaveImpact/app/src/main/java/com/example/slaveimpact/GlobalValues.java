package com.example.slaveimpact;

import android.graphics.drawable.Drawable;

public class GlobalValues {
    public static Object name;
    public static Object level;
    public static Drawable avatar;
    public static int primogems;
    public static MainScreen mainScreenInstance;

    public static MainScreen getMainScreenInstance() {
        return mainScreenInstance;
    }
    public static void setMainScreenInstance(MainScreen mainScreenInstance) {
        GlobalValues.mainScreenInstance = mainScreenInstance;
    }
}
