package com.xjy.hyx.mvpretrofitproject.presenters;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.xjy.hyx.mvpretrofitproject.retrofit.RequestCallBack;
import com.xjy.hyx.mvpretrofitproject.retrofit.RetrofitClient;
import com.xjy.hyx.mvpretrofitproject.entites.Joke;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.JokeViewInterface;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * description:
 * author：xujianye
 * date: 2016/8/13 0013 16:27
 * email：jianyexu@hyx.com
 */
public class JokePresenter extends BasePresenter<JokeViewInterface> {

    public void fetchJokes(int page) {
        getView().showLoading();
        RetrofitClient.getServerApi(RetrofitClient.HOST_JOKE).getJokes(page, getCurrentTimeInString()).enqueue(new RequestCallBack() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                super.onResponse(call, response);
                getView().hideLoading();
                if (!TextUtils.isEmpty(data)) {
                    List<Joke> jokes = gson.fromJson(data, new TypeToken<List<Joke>>() {
                    }.getType());
                    getView().showJokes(jokes);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                super.onFailure(call, t);
                getView().hideLoading();
            }
        });
    }

    public String getCurrentTimeInString() {
        long time = System.currentTimeMillis() / 1000;
        return String.valueOf(time);
    }
}
