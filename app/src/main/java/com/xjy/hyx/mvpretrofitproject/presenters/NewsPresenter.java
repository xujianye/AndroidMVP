package com.xjy.hyx.mvpretrofitproject.presenters;

import com.google.gson.reflect.TypeToken;
import com.xjy.hyx.mvpretrofitproject.entites.News;
import com.xjy.hyx.mvpretrofitproject.retrofit.RequestCallBack;
import com.xjy.hyx.mvpretrofitproject.retrofit.RetrofitClient;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.NewsListViewInterface;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 15:30
 * email：jianyexu@hyx.com
 */
public class NewsPresenter extends BasePresenter<NewsListViewInterface> {

    public void fetchNews(String type) {
        getView().showLoading();
        RetrofitClient.getServerApi().getNews(type).enqueue(new RequestCallBack() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                super.onResponse(call, response);

                getView().hideLoading();
                List<News> newsList = gson.fromJson(data, new TypeToken<List<News>>() {
                }.getType());
                getView().showNews(newsList);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                super.onFailure(call, t);
                getView().hideLoading();
            }
        });
    }

}
