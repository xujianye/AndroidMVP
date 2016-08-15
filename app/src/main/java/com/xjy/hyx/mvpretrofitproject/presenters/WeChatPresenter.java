package com.xjy.hyx.mvpretrofitproject.presenters;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xjy.hyx.mvpretrofitproject.entites.WeChatArticle;
import com.xjy.hyx.mvpretrofitproject.retrofit.RetrofitClient;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.WeChatViewInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 12:24
 * email：jianyexu@hyx.com
 */
public class WeChatPresenter extends BasePresenter<WeChatViewInterface> {

    public void fetchArticles(int page) {
        getView().showLoading();
        RetrofitClient.getServerApi().getWeChatArticles(page).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                getView().hideLoading();
                try {
                    String result = response.body().string();
                    Log.i("onResponse", result);
                    JSONObject jsonObject = new JSONObject(result);
                    String reason = jsonObject.optString("reason");
                    JSONObject result1 = jsonObject.getJSONObject("result");
                    String list = result1.optString("list");
                    int error_code = result1.optInt("error_code");
                    if (error_code == 0 && !TextUtils.isEmpty(list)) {
                        List<WeChatArticle> weChatArticles = new Gson().fromJson(list, new TypeToken<List<WeChatArticle>>(){}.getType());
                        getView().showArticles(weChatArticles);
                    } else {
                        getView().showError(reason);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                getView().hideLoading();
            }
        });
    }
}
