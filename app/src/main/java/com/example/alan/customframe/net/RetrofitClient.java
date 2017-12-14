package com.example.alan.customframe.net;

import com.example.alan.customframe.net.callback.IError;
import com.example.alan.customframe.net.callback.IFailure;
import com.example.alan.customframe.net.callback.IRequest;
import com.example.alan.customframe.net.callback.ISuccess;

import java.util.HashMap;

/**
 * Created by Alan on 2017/12/14.
 */

public class RetrofitClient {

    private final String URL;
    private final HashMap<String,Object> params;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final IError ERROR;


    public RetrofitClient(String URL, HashMap<String, Object> params,
                          IFailure FAILURE, ISuccess SUCCESS,
                          IRequest REQUEST, IError ERROR) {
        this.URL = URL;
        this.params = params;
        this.FAILURE = FAILURE;
        this.SUCCESS = SUCCESS;
        this.REQUEST = REQUEST;
        this.ERROR = ERROR;
    }

    /**
     * 购造单例模式
     */
    private static final class RetrofitHolder{
        public static final RetrofitClientBuilder BUILDER = new RetrofitClientBuilder();
    }

    public static RetrofitClientBuilder builder(){
        return RetrofitHolder.BUILDER;
    }

}
