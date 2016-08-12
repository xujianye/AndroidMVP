package com.xjy.hyx.mvpretrofitproject.models.impl;

import com.xjy.hyx.mvpretrofitproject.entites.Article;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;
import com.xjy.hyx.mvpretrofitproject.models.ArticleModel;

import java.util.LinkedList;
import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 16:18
 * email：jianyexu@hyx.com
 */
public class ArticalModelImpl implements ArticleModel {

    private List<Article> mArticles = new LinkedList<>();

    @Override
    public void saveArticles(List<Article> articles) {
        mArticles.addAll(articles);
    }

    @Override
    public void loadArticlesFromCache(DataListener<List<Article>> listener) {
        listener.onComplete(mArticles);
    }
}
