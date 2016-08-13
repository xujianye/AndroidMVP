package com.xjy.hyx.mvpretrofitproject.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 16:05
 * email：jianyexu@hyx.com
 */
public interface ServerApi {

    @GET("toutiao/index?key=9b96c83a80394a188e5a2d54318227f6")
    Call<ResponseBody> getNews(@Query("type") String type);

    @GET("joke/content/list.from?sort=desc&key=56d2bfcc50f68ee2d766b1d7d34bb284&pagesize=20")
    Call<ResponseBody> getJokes(@Query("page") int page, @Query("time") String time);
}
