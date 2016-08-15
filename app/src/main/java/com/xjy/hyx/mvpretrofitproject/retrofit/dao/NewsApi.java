package com.xjy.hyx.mvpretrofitproject.retrofit.dao;

import com.xjy.hyx.mvpretrofitproject.entites.News;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 17:49
 * email：jianyexu@hyx.com
 */
public interface NewsApi {
    void fetchNews(String type, DataListener<List<News>> dataListener);
}
