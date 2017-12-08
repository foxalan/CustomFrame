package com.example.alan.customframe.application;

import android.app.Application;

import com.example.latten_corn.Latte;

/**
 * Created by Alan on 2017/12/8.
 */

public class FoxApp extends Application {

    private String host = "10.202.13.170";

    @Override
    public void onCreate() {
        super.onCreate();

        Latte.init(this)
                .withApiHost(host)
                .configure();


    }
}
