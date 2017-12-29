package com.example.alan.customframe.database;

import android.content.Context;

/**
 * Function :
 * Modify Date : 2017/12/29
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class DaoManager {

    private String sqliteName ="latte";
    private DaoMaster daoMaster;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoSession daoSession;

    private static class DaoManagerHolder{
        private static final DaoManager DAO_MANAGER = new DaoManager();
    }

    public static DaoManager getInstance(){
        return DaoManagerHolder.DAO_MANAGER;
    }

    public DaoMaster getDaoMaster(Context context){
        if (daoMaster == null){
            devOpenHelper = new DaoMaster.DevOpenHelper(context,sqliteName);
            daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        }

        return daoMaster;
    }

    public DaoSession getDaoSession(Context context){
        if (daoSession == null){
            daoSession = getDaoMaster(context).newSession();
        }
        return daoSession;
    }


}
