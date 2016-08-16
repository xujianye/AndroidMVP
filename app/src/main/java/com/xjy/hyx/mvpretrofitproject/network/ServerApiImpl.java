package com.xjy.hyx.mvpretrofitproject.network;

import com.google.gson.reflect.TypeToken;
import com.xjy.hyx.mvpretrofitproject.entites.Joke;
import com.xjy.hyx.mvpretrofitproject.entites.News;
import com.xjy.hyx.mvpretrofitproject.entites.WeChatArticle;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * description:
 * author：xujianye
 * date: 2016/8/16 0016 11:26
 * email：jianyexu@hyx.com
 */
public class ServerApiImpl implements ServerApi {

    private ResHandler mResHandler;
    private static ServerApiImpl instance;

    private ServerApiImpl() {
        mResHandler = new ResHandler();
    }

    public static ServerApiImpl getInstance() {
        if (instance == null) {
            synchronized (ServerApiImpl.class) {
                if (instance == null) {
                    instance = new ServerApiImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void fetchJokes(int page, final DataListener<List<Joke>> listener) {
        RetrofitClient.getServerApi(RetrofitClient.HOST_JOKE).getJokes(page, getCurrentTimeInString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data = response.body().string();
                    List<Joke> newsList = mResHandler.parseList(data, new TypeToken<List<Joke>>() {
                    }.getType());
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

    @Override
    public void fetchNews(String type, final DataListener<List<News>> listener) {
        RetrofitClient.getServerApi().getNews(type).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String data = response.body().string();
                    List<News> newsList = mResHandler.parseList(data, new TypeToken<List<News>>() {
                    }.getType());
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

    @Override
    public void fetchWeChatArticles(int page, final DataListener<List<WeChatArticle>> listener) {
        RetrofitClient.getServerApi().getWeChatArticles(page).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    List<WeChatArticle> weChatArticles = mResHandler.parseList(result, "list", new TypeToken<List<WeChatArticle>>() {
                    }.getType());
                    listener.onComplete(weChatArticles);
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

    /**
     * 获取当前时间（秒），并以String类型返回
     *
     * @return
     */
    public String getCurrentTimeInString() {
        long time = System.currentTimeMillis() / 1000;
        return String.valueOf(time);
    }
}
