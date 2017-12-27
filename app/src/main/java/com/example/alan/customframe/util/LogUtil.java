package com.example.alan.customframe.util;

import android.util.Log;

/**
 * Function : Log工具
 * Modify Date : 2017/12/27
 * Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class LogUtil {
    private static String name = "tang";

    public static void e(String context){
        e(name,context);
    }

    public static void e(String name,String context){
        Log.e(name,context);
    }

}
