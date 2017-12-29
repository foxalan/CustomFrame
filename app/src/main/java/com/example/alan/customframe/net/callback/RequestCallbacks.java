package com.example.alan.customframe.net.callback;


import com.example.alan.customframe.loading.LatteLoader;
import com.example.alan.customframe.loading.LoadingIndicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Alan
 *
 */


public class RequestCallbacks implements Callback<String> {

    private IFailure failure;
    private ISuccess success;
    private IRequest request;
    private IError error;
    private LoadingIndicator indicator;

    public RequestCallbacks(IFailure failure, ISuccess success, IRequest request, IError error, LoadingIndicator indicator) {
        this.failure = failure;
        this.success = success;
        this.request = request;
        this.error = error;
        this.indicator = indicator;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {

        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                success.onSuccess(response.body());
                if (request != null) {
                    request.onEnd();
                }
            }
        } else {
            if (error != null) {
                error.onError(response.code(), response.message());
                if (request != null) {
                    request.onEnd();
                }
            }
        }

        if (indicator != null) {
            LatteLoader.stopLoading();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (failure != null) {
            failure.IFailure();
            if (request!=null){
                request.onEnd();
            }
        }
    }
}
