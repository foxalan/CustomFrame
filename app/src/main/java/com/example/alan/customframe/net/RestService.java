package com.example.alan.customframe.net;

import com.squareup.okhttp.ResponseBody;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Alan on 2017/12/14.
 */

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);

    /**
     * FormUrlEncoded 请求体是一个FORM表单
     * @param url
     * @param params
     * @return
     */
    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String,Object> params);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String,Object> params);

    /**
     * Streaming表示返回的数据以流的形式返回，不加可能会造成OOM
     * @param url
     * @param params
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String,Object> params);

    // to do

    /**
     * Multipart 表示请求体是一个支持上传的表单
     * @param url
     * @param file
     * @return
     */
    @Multipart
    @GET
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);


}
