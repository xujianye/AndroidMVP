package com.xjy.hyx.mvpretrofitproject.presenters;

import com.xjy.hyx.mvpretrofitproject.entites.WeChatArticle;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.WeChatViewInterface;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 12:24
 * email：jianyexu@hyx.com
 */
public class WeChatPresenter extends BasePresenter<WeChatViewInterface> {

    public void fetchArticles(int page) {
        getView().showLoading();
        mServerApi.fetchWeChatArticles(page, new DataListener<List<WeChatArticle>>() {
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
