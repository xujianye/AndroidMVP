package com.xjy.hyx.mvpretrofitproject.ui.interfaces;

import com.xjy.hyx.mvpretrofitproject.entites.Joke;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/13 0013 16:28
 * email：jianyexu@hyx.com
 */
public interface JokeViewInterface {
    void showLoading();

    void hideLoading();

    void showJokes(List<Joke> jokes);
}
