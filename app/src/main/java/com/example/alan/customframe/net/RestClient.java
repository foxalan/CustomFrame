package com.example.alan.customframe.net;


import com.example.alan.customframe.net.callback.IError;
import com.example.alan.customframe.net.callback.IFailure;
import com.example.alan.customframe.net.callback.IRequest;
import com.example.alan.customframe.net.callback.ISuccess;
import com.example.alan.customframe.net.callback.RequestCallbacks;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Alan on 2017/12/14.
 */

public class RestClient {

    private final String URL;
    private final HashMap<String, Object> params;
    private final IFailure FAILURE;
    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final IError ERROR;


    public RestClient(String URL, HashMap<String, Object> params,
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
    private static final class RetrofitHolder {
        public static final RestClientBuilder BUILDER = new RestClientBuilder();
    }

    public static RestClientBuilder builder() {
        return RetrofitHolder.BUILDER;
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getService();
        Call<String> call = null;
        if (REQUEST == null) {
            REQUEST.onStart();
        }
        switch (method) {
            case GET:
                call = service.get(URL, params);
                break;
            case POST:
                break;
            case PUT:
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
        return new RequestCallbacks(FAILURE, SUCCESS, REQUEST, ERROR);
    }

    public final void get() {
        request(HttpMethod.GET);
    }


}
