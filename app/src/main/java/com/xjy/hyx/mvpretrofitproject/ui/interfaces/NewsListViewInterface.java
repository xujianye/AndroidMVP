package com.xjy.hyx.mvpretrofitproject.ui.interfaces;

import com.xjy.hyx.mvpretrofitproject.entites.Article;
import com.xjy.hyx.mvpretrofitproject.entites.News;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 16:15
 * email：jianyexu@hyx.com
 */
public interface NewsListViewInterface {

    void showLoading();

    void hideLoading();

    void showNews(List<News> newsList);
}
