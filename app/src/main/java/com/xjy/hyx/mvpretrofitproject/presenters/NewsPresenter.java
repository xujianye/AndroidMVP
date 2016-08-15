package com.xjy.hyx.mvpretrofitproject.presenters;

import com.xjy.hyx.mvpretrofitproject.entites.News;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;
import com.xjy.hyx.mvpretrofitproject.retrofit.dao.NewsApi;
import com.xjy.hyx.mvpretrofitproject.retrofit.impl.NewsApiImpl;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.NewsListViewInterface;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 15:30
 * email：jianyexu@hyx.com
 */
public class NewsPresenter extends BasePresenter<NewsListViewInterface> {

    private NewsApi mNewApi = new NewsApiImpl();

    public void fetchNews(String type) {
        getView().showLoading();
        mNewApi.fetchNews(type, new DataListener<List<News>>() {
            @Override
            public void onComplete(List<News> result) {
                getView().hideLoading();
                if (result != null) {
                    getView().showNews(result);
                }
            }
        });
    }

}
