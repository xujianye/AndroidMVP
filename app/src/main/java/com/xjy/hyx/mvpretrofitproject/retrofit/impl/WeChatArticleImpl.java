package com.xjy.hyx.mvpretrofitproject.retrofit.impl;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xjy.hyx.mvpretrofitproject.entites.WeChatArticle;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;
import com.xjy.hyx.mvpretrofitproject.retrofit.RetrofitClient;
import com.xjy.hyx.mvpretrofitproject.retrofit.dao.WeChatArticleApi;
import com.xjy.hyx.mvpretrofitproject.retrofit.handler.ResHandler;
import com.xjy.hyx.mvpretrofitproject.retrofit.handler.WeChatArticleHandler;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 18:01
 * email：jianyexu@hyx.com
 */
public class WeChatArticleImpl implements WeChatArticleApi {

    private ResHandler mResHandler;

    public WeChatArticleImpl() {
        mResHandler = new WeChatArticleHandler();
    }

    @Override
    public void fetchWeChatArticles(int page, final DataListener<List<WeChatArticle>> listener) {
        RetrofitClient.getServerApi().getWeChatArticles(page).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    List<WeChatArticle> weChatArticles = (List<WeChatArticle>) mResHandler.parse(result);
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
}
