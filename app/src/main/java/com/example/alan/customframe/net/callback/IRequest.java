package com.example.alan.customframe.net.callback;

/**
 * Created by Alan on 2017/12/14.
 */

public interface IRequest {
    /**
     * 请求开始的回调
     */
    void onStart();

    /**
     * 请求结束的回调
     */
    void onEnd();
}
