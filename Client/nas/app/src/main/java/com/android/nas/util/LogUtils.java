package com.android.nas.util;

import android.util.Log;

public class LogUtils {
    public static void myLod(Class clazz, String functionName, String str){
        Log.d("myTAG", "[" + clazz.getName() + " -> " + functionName + "]   " + str);
    }
    public static void myLod(Class clazz, String functionName){
        Log.d("myTAG", "[" + clazz.getName() + " -> " + functionName + "]   ");
    }
}
