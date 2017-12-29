package com.example.alan.customframe.net.callback;

/**
 * Created by Alan on 2017/12/14.
 */

public interface IError {

    /**
     * 数据请求错误的回调
     * @param code
     * @param message
     */
    void onError(int code ,String message);
}
