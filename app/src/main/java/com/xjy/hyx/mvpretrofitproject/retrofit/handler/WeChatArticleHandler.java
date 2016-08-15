package com.xjy.hyx.mvpretrofitproject.retrofit.handler;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xjy.hyx.mvpretrofitproject.entites.WeChatArticle;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 18:03
 * email：jianyexu@hyx.com
 */
public class WeChatArticleHandler implements ResHandler<List<WeChatArticle>, String> {

    @Override
    public List<WeChatArticle> parse(String result) {
        Log.i("onResponse", result);
        List<WeChatArticle> weChatArticles = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject result1 = jsonObject.getJSONObject("result");
            String list = result1.optString("list");
            int error_code = result1.optInt("error_code");
            if (error_code == 0 && !TextUtils.isEmpty(list)) {
                weChatArticles = new Gson().fromJson(list, new TypeToken<List<WeChatArticle>>(){}.getType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weChatArticles;
    }
}
