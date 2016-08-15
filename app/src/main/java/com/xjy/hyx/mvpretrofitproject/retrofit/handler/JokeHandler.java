package com.xjy.hyx.mvpretrofitproject.retrofit.handler;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xjy.hyx.mvpretrofitproject.entites.Joke;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 16:55
 * email：jianyexu@hyx.com
 */
public class JokeHandler implements ResHandler<List<Joke>, String> {

    @Override
    public List<Joke> parse(String result) {
        List<Joke> jokes = new ArrayList<>();
        try {
            Log.i("onResponse", result);
            JSONObject jsonObject = new JSONObject(result);
            JSONObject result1 = jsonObject.getJSONObject("result");
            String data = result1.optString("data");
            if (!TextUtils.isEmpty(data)) {
                jokes = new Gson().fromJson(data, new TypeToken<List<Joke>>() {
                }.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jokes;
    }
}
