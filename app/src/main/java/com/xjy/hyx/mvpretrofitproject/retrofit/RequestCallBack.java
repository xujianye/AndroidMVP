package com.xjy.hyx.mvpretrofitproject.retrofit;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 17:33
 * email：jianyexu@hyx.com
 */
public abstract class RequestCallBack implements Callback<ResponseBody> {

    protected String data; // 数据
    protected String reason; // 错误信息
    protected String stat; // 状态码
    protected Gson gson = new Gson();
    protected static final String OK = "1";

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            String result = response.body().string();
            Log.i("onResponse", result);
            JSONObject jsonObject = new JSONObject(result);
            reason = jsonObject.optString("reason");
            JSONObject result1 = jsonObject.getJSONObject("result");
            data = result1.optString("data");
            stat = result1.optString("stat");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }
}
