package com.caesarlib.brvahbinding;

import android.util.Log;

public class CSLog {
    private static boolean isOpen;
    private static boolean isI;

    public static void Print(String str) {
        if (isOpen) {
            if (isI) {
                Log.i("caesarLog", str);
            } else {
                Log.d("caesarLog", str);
            }
        }
    }


    public static void setLeverI() {
        isI = true;
    }

    public static void Open() {
        isOpen = true;
    }
}
