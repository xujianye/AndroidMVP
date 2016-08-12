package com.xjy.hyx.mvpretrofitproject.retrofit;

import android.util.Log;

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

    public String data; // 数据
    public String msg; // 错误信息
    public String status; // 状态码
    protected static final String STATUS_OK = "0";
    protected static final String STATUS_EMPTY = "6003"; // 服务器无数据

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try {
            String result = response.body().string();
            Log.i("onResponse", result);
            JSONObject jsonObject = new JSONObject(result);
            data = jsonObject.optString("data");
            msg = jsonObject.optString("msg");
            status = jsonObject.optString("status");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }
}
