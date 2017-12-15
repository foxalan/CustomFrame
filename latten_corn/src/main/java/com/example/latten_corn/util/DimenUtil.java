package com.example.latten_corn.util;


import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.latten_corn.Latte;

public class DimenUtil {

    public static int getScreenWidth(){

        return getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(){

        return getDisplayMetrics().heightPixels;
    }

    private static DisplayMetrics getDisplayMetrics(){

        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();

        return dm;
    }
}
