package com.xjy.hyx.mvpretrofitproject.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 16:53
 * email：jianyexu@hyx.com
 */
public class RetrofitClient {

    private static final String TAG = "RetrofitClient";
    public static final String HOST_NEWS = "http://v.juhe.cn/";
    public static final String HOST_JOKE = "http://japi.juhe.cn/";
    private static RetrofitApi mRetrofitApi;

    public static RetrofitApi getServerApi() {
        if (mRetrofitApi == null) {
            synchronized (TAG) {
                if (mRetrofitApi == null) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(HOST_NEWS)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    mRetrofitApi = retrofit.create(RetrofitApi.class);
                }
            }
        }
        return mRetrofitApi;
    }

    public static RetrofitApi getServerApi(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitApi.class);
    }
}
