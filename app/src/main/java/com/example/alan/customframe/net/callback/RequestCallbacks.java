package com.example.alan.customframe.net.callback;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alan on 2017/12/14.
 */


public class RequestCallbacks implements Callback<String> {

    private IFailure failure;
    private ISuccess success;
    private IRequest request;
    private IError error;

    public RequestCallbacks(IFailure failure, ISuccess success, IRequest request, IError error) {
        this.failure = failure;
        this.success = success;
        this.request = request;
        this.error = error;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {

        if (response.isSuccessful()) {
            if (call.isExecuted()) {

                success.onSuccess(response.body());
            }
        } else {
            error.onError(response.code(), response.message());
        }

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }
}
