package com.example.alan.customframe.database;

import android.content.Context;

/**
 * Function :用户数据库的操作方式
 * Modify Date : 2017/12/29
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class UserInfoDaoUtil {

    private Context context;

    public UserInfoDaoUtil(Context context){
        this.context = context;
    }

    public void insertUserInfo(UserInfo info){
        DaoManager.getInstance().getDaoSession(context).getUserInfoDao().insert(info);
    }
}
