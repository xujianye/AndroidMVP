package com.xjy.hyx.mvpretrofitproject.ui.interfaces;

import com.xjy.hyx.mvpretrofitproject.entites.Article;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 16:15
 * email：jianyexu@hyx.com
 */
public interface ArticleViewInterface {

    void showArticles(List<Article> articles);

    void showLoading();

    void hideLoading();
}
