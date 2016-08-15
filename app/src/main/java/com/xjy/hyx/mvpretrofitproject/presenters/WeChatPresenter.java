package com.xjy.hyx.mvpretrofitproject.presenters;

import com.xjy.hyx.mvpretrofitproject.entites.WeChatArticle;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;
import com.xjy.hyx.mvpretrofitproject.retrofit.dao.WeChatArticleApi;
import com.xjy.hyx.mvpretrofitproject.retrofit.impl.WeChatArticleImpl;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.WeChatViewInterface;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 12:24
 * email：jianyexu@hyx.com
 */
public class WeChatPresenter extends BasePresenter<WeChatViewInterface> {

    private WeChatArticleApi mWeChatArticleApi = new WeChatArticleImpl();

    public void fetchArticles(int page) {
        getView().showLoading();
        mWeChatArticleApi.fetchWeChatArticles(page, new DataListener<List<WeChatArticle>>() {
            @Override
            public void onComplete(List<WeChatArticle> result) {
                getView().hideLoading();
                if (result != null) {
                    getView().showArticles(result);
                }
            }
        });
    }
}
