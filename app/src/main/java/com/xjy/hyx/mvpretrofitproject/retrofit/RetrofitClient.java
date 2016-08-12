package com.xjy.hyx.mvpretrofitproject.retrofit;

import okhttp3.OkHttpClient;
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
    public static final String HOST = "http://mobileiftest2.yuwanchat.com";
    private static ServerApi mServerApi;

    public static ServerApi getServerApi() {
        if (mServerApi == null) {
            synchronized (TAG) {
                if (mServerApi == null) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl(HOST)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    mServerApi = retrofit.create(ServerApi.class);
                }
            }
        }
        return mServerApi;
    }
}
