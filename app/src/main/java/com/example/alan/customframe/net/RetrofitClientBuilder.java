package com.example.alan.customframe.net;

import com.example.alan.customframe.net.callback.IError;
import com.example.alan.customframe.net.callback.IFailure;
import com.example.alan.customframe.net.callback.IRequest;
import com.example.alan.customframe.net.callback.ISuccess;

import java.util.HashMap;

/**
 * Created by Alan on 2017/12/14.
 */

public class RetrofitClientBuilder {

    private String url;
    private HashMap<String, Object> params;
    private IFailure failure;
    private ISuccess success;
    private IRequest request;
    private IError error;


    public void setUrl(String url) {
        this.url = url;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public void setFailure(IFailure failure) {
        this.failure = failure;
    }

    public void setSuccess(ISuccess success) {
        this.success = success;
    }

    public void setRequest(IRequest request) {
        this.request = request;
    }

    public void setError(IError error) {
        this.error = error;
    }

    public RetrofitClient build(){
        return new RetrofitClient(url,params,failure,success,request,error);
    }
}
