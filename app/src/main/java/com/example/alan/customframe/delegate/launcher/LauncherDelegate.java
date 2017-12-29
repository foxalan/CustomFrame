package com.example.alan.customframe.delegate.launcher;

import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;

import com.example.alan.customframe.R;
import com.example.alan.customframe.Timer.BaseTimerTask;
import com.example.alan.customframe.Timer.ITimerCallBack;
import com.example.alan.customframe.config.ConfigType;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.login.CheckLoginMessage;
import com.example.alan.customframe.login.ISignCallBack;
import com.example.alan.customframe.util.LogUtil;
import com.example.alan.customframe.util.PreferenceUtil;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function : 启动页面
 * Modify Date : 2017/12/27
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class LauncherDelegate extends LatteDelegate implements ITimerCallBack {

    @BindView(R.id.tv_launcher_time)
    AppCompatTextView appCompatTextView;

    @OnClick(R.id.tv_launcher_time)
    void onLauncherTimeClick(){
        LogUtil.e("launcher on click");
    }

    private Timer timer = null;
    private BaseTimerTask baseTimerTask = null;
    private int totalTime = 5;
    private boolean isFirstEnter;
    private ISignCallBack signCallBack = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof  ISignCallBack){
            signCallBack = (ISignCallBack) activity;
        }
    }

    @Override
    public Object getLayout() {
        return R.layout.delegate_launcher;
    }

    private void initTimer() {
        timer = new Timer();
        baseTimerTask = new BaseTimerTask(this);
        timer.schedule(baseTimerTask, 0, 1000);
    }

    @Override
    public void onBindView() {
        isFirstEnter = PreferenceUtil.getBoolean(ConfigType.IS_FIRST_ENTER.name(),true);
        initTimer();
    }

    //todo

    @Override
    public void onTimer() {
        appCompatTextView.setText("跳过" + totalTime + "s");
        totalTime--;
        if (totalTime < 0) {
            if (timer != null) {
                timer.cancel();
                if (isFirstEnter){
                    start(new AdvertisementDelegate(),SINGLETASK);
                    PreferenceUtil.putBoolean(ConfigType.IS_FIRST_ENTER.name(),false);
                }else {
                    //todo
                    if (CheckLoginMessage.checkLogin()){
                        signCallBack.onSignIn();
                    }else {
                        signCallBack.onSignOut();
                    }
                }
            }
        }
    }


}
