package com.example.latten_corn.http;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alan on 2017/12/13.
 * 网络请求框架 Volley
 */

public class VolleyHttpUtil {

    private static String result;
    private static JSONObject jsonObject;

    /**
     * Get请求
     *
     * @param context
     * @param url
     * @return
     */
    public static String get(Context context, String url) {


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                result = response;
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result = "";
                throw new RuntimeException("the net error");
            }
        });

        requestQueue.add(request);

        return result;
    }

    /**
     * Post请求
     *
     * @param context
     * @param url
     * @param key
     * @param value
     * @return
     */
    public static String post(Context context, String url, final String key, final String value) {

        RequestQueue mQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                result = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                result = "";
            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put(key, value);
                return map;
            }
        };

        mQueue.add(request);
        return result;
    }


    /**
     * 返回JSONObject的请求
     */

    public static JSONObject getJsonObject(Context context, String url) {
        RequestQueue mQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                jsonObject = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                jsonObject = null;
            }
        });

        mQueue.add(jsonObjectRequest);

        return jsonObject;
    }


}
