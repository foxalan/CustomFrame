package com.example.alan.customframe.net.callback;

/**
 * @author Alan
 */

public interface IError {

    /**
     * 数据请求错误的回调
     * @param code
     * @param message
     */
    void onError(int code ,String message);
}
