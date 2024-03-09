package com.example.slaveimpact;

public class GlobalValues {
    //    public static Object name;
//    public static Object level;
//    public static Drawable avatar;
    public static int primogems;

    // access the MainScreen instance globally
    public static MainScreen mainScreenInstance;

    public static MainScreen getMainScreenInstance() {
        return mainScreenInstance;
    }

    public static void setMainScreenInstance(MainScreen mainScreenInstance) {
        GlobalValues.mainScreenInstance = mainScreenInstance;
    }
}
