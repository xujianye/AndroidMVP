package com.xjy.hyx.mvpretrofitproject.retrofit.handler;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xjy.hyx.mvpretrofitproject.entites.Joke;
import com.xjy.hyx.mvpretrofitproject.entites.News;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 16:55
 * email：jianyexu@hyx.com
 */
public class NewsHandler implements ResHandler<List<News>, String> {

    @Override
    public List<News> parse(String result) {
        List<News> jokes = new ArrayList<>();
        try {
            Log.i("onResponse", result);
            JSONObject jsonObject = new JSONObject(result);
            JSONObject result1 = jsonObject.getJSONObject("result");
            String data = result1.optString("data");
            if (!TextUtils.isEmpty(data)) {
                jokes = new Gson().fromJson(data, new TypeToken<List<News>>() {
                }.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jokes;
    }
}
