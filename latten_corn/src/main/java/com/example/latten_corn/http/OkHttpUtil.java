package com.example.latten_corn.http;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Alan on 2017/12/13.
 */

public class OkHttpUtil {

    private static String result;

    /**
     * Get请求
     */

    public static String get(String url) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(url)
                .build();

        //new call
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                result = "";
            }

            @Override
            public void onResponse(Response response) throws IOException {
                result = response.body().toString();
            }
        });

        return result;
    }

    /**
     * Post请求
     * @param url
     * @param key
     * @param value
     * @return
     */
    public static String post(String url,String key,String value){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add(key,value);
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                . build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                result = "";
            }

            @Override
            public void onResponse(Response response) throws IOException {
                result = response.body().toString();
            }
        });

        return result;
    }
}
