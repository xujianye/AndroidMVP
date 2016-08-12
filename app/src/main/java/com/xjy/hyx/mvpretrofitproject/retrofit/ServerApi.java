package com.xjy.hyx.mvpretrofitproject.retrofit;

import com.xjy.hyx.mvpretrofitproject.retrofit.entites.ReqArticle;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 16:05
 * email：jianyexu@hyx.com
 */
public interface ServerApi {

    // 查询首页 主题活动列表
    @POST("ver4/bcnew/v1_9/subject/getList")
    Call<ResponseBody> getArticles(@Body ReqArticle article);

    @GET("toutiao/index?key=9b96c83a80394a188e5a2d54318227f6")
    Call<ResponseBody> getNews(@Query("type") String type);

}
