package com.example.latten_corn;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by Alan on 2017/12/8.
 */

public class Latte {


    //得到实例
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());

        return Configurator.getInstance();

    }


    private static WeakHashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }


}
