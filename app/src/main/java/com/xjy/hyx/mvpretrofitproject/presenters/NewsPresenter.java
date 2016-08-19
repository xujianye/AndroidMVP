package com.xjy.hyx.mvpretrofitproject.presenters;

import com.xjy.hyx.mvpretrofitproject.entites.News;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.NewsListViewInterface;
import com.xjy.hyx.mvpretrofitproject.utils.DataUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 15:30
 * email：jianyexu@hyx.com
 */
public class NewsPresenter extends BasePresenter<NewsListViewInterface> {

    public void fetchNews(String type) {
        getView().showLoading();
        mServerApi.fetchNews(type, new DataListener<List<News>>() {
            @Override
            public void onComplete(List<News> result) {
                getView().hideLoading();
                if (result != null) {
                    sort(result);
                    getView().showNews(result);
                }
            }
        });
    }

    /**
     * 排序
     * @param newsList
     */
    private void sort(List<News> newsList) {
        /*
         * int compare(News o1, News o2) 返回一个基本类型的整型，
         * 返回负数表示：o1 小于o2，
         * 返回0 表示：o1和o2相等，
         * 返回正数表示：o1大于o2。
         */
        Collections.sort(newsList, new Comparator<News>() {
            @Override
            public int compare(News lhs, News rhs) {
                long lTime = DataUtils.getTime(lhs.date);
                long rTime = DataUtils.getTime(rhs.date);
                if (lTime > rTime) {
                    return -1;
                } else if (lTime < rTime) {
                    return 1;
                }
                return 0;
            }
        });
    }

}
