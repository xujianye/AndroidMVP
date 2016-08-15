package com.xjy.hyx.mvpretrofitproject.retrofit.impl;

import com.xjy.hyx.mvpretrofitproject.entites.Joke;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;
import com.xjy.hyx.mvpretrofitproject.retrofit.RetrofitClient;
import com.xjy.hyx.mvpretrofitproject.retrofit.dao.JokeApi;
import com.xjy.hyx.mvpretrofitproject.retrofit.handler.JokeHandler;
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
 * date: 2016/8/15 0015 16:41
 * email：jianyexu@hyx.com
 */
public class JokeApiImpl implements JokeApi {

    private ResHandler mResHandler;

    public JokeApiImpl() {
        mResHandler = new JokeHandler();
    }

    @Override
    public void fetchJokes(int page, final DataListener<List<Joke>> listener) {
        RetrofitClient.getServerApi(RetrofitClient.HOST_JOKE).getJokes(page, getCurrentTimeInString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data = response.body().string();
                    List<Joke> newsList = (List<Joke>) mResHandler.parse(data);
                    listener.onComplete(newsList);
                } catch (IOException e) {
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

    public String getCurrentTimeInString() {
        long time = System.currentTimeMillis() / 1000;
        return String.valueOf(time);
    }
}
