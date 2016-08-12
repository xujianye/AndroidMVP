package com.xjy.hyx.mvpretrofitproject.models;

import com.xjy.hyx.mvpretrofitproject.entites.Article;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 16:18
 * email：jianyexu@hyx.com
 */
public interface ArticleModel {

    void saveArticles(List<Article> articles);
    void loadArticlesFromCache(DataListener<List<Article>> listener);
}
