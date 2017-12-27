package com.example.alan.customframe.Timer;

import java.util.TimerTask;

/**
 * Function : 时间工具
 * Modify Date : 2017/12/27
 * Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class BaseTimerTask extends TimerTask {

    private ITimerCallBack timerCallBack;

    public BaseTimerTask(ITimerCallBack timerCallBack) {
        this.timerCallBack = timerCallBack;
    }

    @Override
    public void run() {
        timerCallBack.onTimer();
    }
}
