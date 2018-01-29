package com.example.alan.customframe.latte;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author Alan
 * Created by Alan on 2017/12/8.
 * 1.关于字体的拓展
 * 2.Reference与ReferenceQuene
 * 3.网络生成JSON给控制器生成所需数据在给Adapter(理清之间的关系)
 */

public class FoxApp extends Application {

    private String host = "http://10.203.147.140:8080/RestServer/api/";

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {

        /**
         *  初始化基本配置
         *  1.字体
         *  2.服务器地址
         *  3.加载时间
         *  4.与Javascript的方法
         */
        Latte.init(this)
                .withIconFont(new FontAwesomeModule())
                .withApiHost(host)
                .withLoaderTime(1000)
                .withJavascriptInterface("latte")
                .configure();

        //初始化内存检测包
        LeakCanary.install(this);

    }
}
