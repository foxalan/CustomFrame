package com.example.alan.customframe.delegate.launcher;

import android.support.v7.widget.AppCompatTextView;

import com.example.alan.customframe.R;
import com.example.alan.customframe.Timer.BaseTimerTask;
import com.example.alan.customframe.Timer.ITimerCallBack;
import com.example.alan.customframe.delegate.LatteDelegate;
import com.example.alan.customframe.util.LogUtil;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Function :
 * Modify Date : 2017/12/27
 * Author : Alan
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
    private int total_time = 5;

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
        initTimer();
    }

    //todo
    //做了一个runOnUiThread的处理
    @Override
    public void onTimer() {
        appCompatTextView.setText("跳过" + total_time + "s");
        total_time--;
        if (total_time < 0) {
            if (timer != null) {
                timer.cancel();
            }
        }
    }
}
