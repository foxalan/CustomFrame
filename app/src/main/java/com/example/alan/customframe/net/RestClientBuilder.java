package com.example.alan.customframe.net;

import com.example.alan.customframe.net.callback.IError;
import com.example.alan.customframe.net.callback.IFailure;
import com.example.alan.customframe.net.callback.IRequest;
import com.example.alan.customframe.net.callback.ISuccess;

import java.util.HashMap;

/**
 * Created by Alan on 2017/12/14.
 */

public class RestClientBuilder {

    private String url;
    private HashMap<String, Object> params;
    private IFailure failure;
    private ISuccess success;
    private IRequest request;
    private IError error;


    public RestClientBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public RestClientBuilder setParams(HashMap<String, Object> params) {
        this.params = params;
        return this;
    }

    public RestClientBuilder setFailure(IFailure failure) {
        this.failure = failure;
        return this;
    }

    public RestClientBuilder setSuccess(ISuccess success) {
        this.success = success;
        return this;
    }

    public RestClientBuilder setRequest(IRequest request) {
        this.request = request;
        return this;
    }

    public RestClientBuilder setError(IError error) {
        this.error = error;
        return this;
    }

    public RestClient build() {
        return new RestClient(url, params, failure, success, request, error);
    }
}