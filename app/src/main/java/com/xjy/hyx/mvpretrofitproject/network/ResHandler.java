package com.xjy.hyx.mvpretrofitproject.network;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 16:51
 * email：jianyexu@hyx.com
 */
public class ResHandler{

    public static final String DEFAULT_DATA = "data";
    private Gson mGson = new Gson();

    public <T> T parseList(String result, Type type){
        return parseList(result, DEFAULT_DATA, type);
    }

    public <T> T parseList(String result, String flag, Type type){
        String data = getData(result, flag);
        if (!TextUtils.isEmpty(data)) {
            return mGson.fromJson(data, type);
        }
        return null;
    }

    public <T> T parseBean(Class<T> t, String result){
        return parseBean(t, result, DEFAULT_DATA);
    }

    public <T> T parseBean(Class<T> t, String result, String flag){
        String data = getData(result, flag);
        if (!TextUtils.isEmpty(data)) {
            return mGson.fromJson(data, t);
        }
        return null;
    }

    private String getData(String result, String flag) {
        try {
            Log.i("onResponse", result);
            JSONObject jsonObject = new JSONObject(result);
            JSONObject result1 = jsonObject.getJSONObject("result");
            String list = result1.optString(flag);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
