package com.xjy.hyx.mvpretrofitproject.retrofit.dao;

import com.xjy.hyx.mvpretrofitproject.entites.WeChatArticle;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 18:00
 * email：jianyexu@hyx.com
 */
public interface WeChatArticleApi {

    void fetchWeChatArticles(int page, DataListener<List<WeChatArticle>> listener);
}
