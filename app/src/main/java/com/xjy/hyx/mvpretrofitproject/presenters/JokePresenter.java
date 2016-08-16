package com.xjy.hyx.mvpretrofitproject.presenters;

import com.xjy.hyx.mvpretrofitproject.entites.Joke;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.JokeViewInterface;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/13 0013 16:27
 * email：jianyexu@hyx.com
 */
public class JokePresenter extends BasePresenter<JokeViewInterface> {

    public void fetchJokes(int page) {
        getView().showLoading();
        mServerApi.fetchJokes(page, new DataListener<List<Joke>>() {
            @Override
            public void onComplete(List<Joke> result) {
                getView().hideLoading();
                if (result != null) {
                    getView().showJokes(result);
                }
            }
        });
    }
}
