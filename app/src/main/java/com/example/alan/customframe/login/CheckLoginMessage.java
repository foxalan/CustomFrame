package com.example.alan.customframe.login;

import com.example.alan.customframe.config.ConfigType;
import com.example.alan.customframe.util.PreferenceUtil;

/**
 * Function : 判斷用户是否登录
 * Modify Date : 2017/12/29
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class CheckLoginMessage {

    public static boolean checkLogin(){

        return PreferenceUtil.getBoolean(ConfigType.HAS_LOGIN.name(),false);
    }

    public static void setLoginTrue(){
        PreferenceUtil.getBoolean(ConfigType.HAS_LOGIN.name(),true);
    }
}
