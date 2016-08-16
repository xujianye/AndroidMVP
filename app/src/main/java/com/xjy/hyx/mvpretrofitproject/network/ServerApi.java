package com.xjy.hyx.mvpretrofitproject.network;

import com.xjy.hyx.mvpretrofitproject.entites.Joke;
import com.xjy.hyx.mvpretrofitproject.entites.News;
import com.xjy.hyx.mvpretrofitproject.entites.WeChatArticle;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/16 0016 11:25
 * email：jianyexu@hyx.com
 */
public interface ServerApi {

    void fetchJokes(int page, DataListener<List<Joke>> listener);

    void fetchNews(String type, DataListener<List<News>> listener);

    void fetchWeChatArticles(int page, DataListener<List<WeChatArticle>> listener);
}
