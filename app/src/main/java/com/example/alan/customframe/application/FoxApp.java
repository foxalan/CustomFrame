package com.example.alan.customframe.application;

import android.app.Application;

import com.example.latten_corn.Latte;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Alan on 2017/12/8.
 * 1.关于字体的拓展
 */

public class FoxApp extends Application {

    private String host = "https://tieba.baidu.com/";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        //初始化基本配置
        Latte.init(this)
                .withIconFont(new FontAwesomeModule())
                .withApiHost(host)
                .configure();

        //初始化内存检测包
        LeakCanary.install(this);
    }
}
