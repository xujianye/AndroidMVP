package com.xjy.hyx.mvpretrofitproject.retrofit.impl;

import com.google.gson.reflect.TypeToken;
import com.xjy.hyx.mvpretrofitproject.entites.News;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;
import com.xjy.hyx.mvpretrofitproject.retrofit.RequestCallBack;
import com.xjy.hyx.mvpretrofitproject.retrofit.RetrofitClient;
import com.xjy.hyx.mvpretrofitproject.retrofit.dao.NewsApi;
import com.xjy.hyx.mvpretrofitproject.retrofit.handler.NewsHandler;
import com.xjy.hyx.mvpretrofitproject.retrofit.handler.ResHandler;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 17:50
 * email：jianyexu@hyx.com
 */
public class NewsApiImpl implements NewsApi {

    private ResHandler mResHandler;

    public NewsApiImpl() {
        mResHandler = new NewsHandler();
    }

    @Override
    public void fetchNews(String type, final DataListener<List<News>> listener) {
        RetrofitClient.getServerApi().getNews(type).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    List<News> newsList = (List<News>) mResHandler.parse(response.body().string());
                    listener.onComplete(newsList);
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.onComplete(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onComplete(null);
            }
        });
    }
}
