package com.xjy.hyx.mvpretrofitproject.retrofit.dao;

import com.xjy.hyx.mvpretrofitproject.entites.Joke;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 16:41
 * email：jianyexu@hyx.com
 */
public interface JokeApi {
    void fetchJokes(int page, DataListener<List<Joke>> dataListener);
}
