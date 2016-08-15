package com.xjy.hyx.mvpretrofitproject.ui.interfaces;

import com.xjy.hyx.mvpretrofitproject.entites.WeChatArticle;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 12:24
 * email：jianyexu@hyx.com
 */
public interface WeChatViewInterface {

    void showLoading();

    void hideLoading();

    void showArticles(List<WeChatArticle> weChatArticles);

    void showError(String errMsg);
}
