package com.example.alan.customframe.net;


import android.content.Context;
import android.util.Log;

import com.example.alan.customframe.loading.LatteLoader;
import com.example.alan.customframe.loading.LoadingIndicator;
import com.example.alan.customframe.net.callback.IError;
import com.example.alan.customframe.net.callback.IFailure;
import com.example.alan.customframe.net.callback.IRequest;
import com.example.alan.customframe.net.callback.ISuccess;
import com.example.alan.customframe.net.callback.RequestCallbacks;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;


/**
 * @author Alan
 */

public class RestClient {

    private final String URL;
    private final HashMap<String, Object> params;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final IError ERROR;
    private final LoadingIndicator INDICATOR;
    private final Context CONTEXT;

    private static final String TAG = "RestClient";


    public RestClient(String URL, HashMap<String, Object> params,
                      IFailure failure, ISuccess success,
                      IRequest request, IError error,
                      LoadingIndicator indicator, Context context) {
        this.URL = URL;
        this.params = params;
        this.FAILURE = failure;
        this.SUCCESS = success;
        this.REQUEST = request;
        this.ERROR = error;
        this.INDICATOR = indicator;
        this.CONTEXT = context;
    }

    /**
     * 购造单例模式
     */
    private static final class RetrofitHolder {
        private static final RestClientBuilder BUILDER = new RestClientBuilder();
    }

    public static RestClientBuilder builder() {
        return RetrofitHolder.BUILDER;
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onStart();
        }
        if (INDICATOR != null){
            Log.e(TAG, "request: "+"show loading" );
            LatteLoader.showLoading(CONTEXT,INDICATOR.name());
        }
        switch (method) {
            case GET:
                call = service.get(URL, params);
                break;
            case POST:
                call = service.post(URL,params);
                break;
            case PUT:
                call = service.put(URL,params);
                break;
            case UPLOAD:

                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack() {
        return new RequestCallbacks(FAILURE, SUCCESS, REQUEST, ERROR, INDICATOR);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post(){request(HttpMethod.POST);}

    public final void put(){request(HttpMethod.PUT);}




}
