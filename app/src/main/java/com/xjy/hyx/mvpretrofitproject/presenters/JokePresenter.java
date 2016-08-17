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

    private boolean isFirst = true;

    public void fetchJokes() {
        getView().showLoading();
        mServerApi.fetchJokes(getRandomNum(100), new DataListener<List<Joke>>() {
            @Override
            public void onComplete(List<Joke> result) {
                getView().hideLoading();
                if (result != null) {
                    getView().showJokes(result);
                }
            }
        });
    }

    /**
     * 获取随机数
     * @param number 取值范围（1 ~ number）
     * @return
     */
    private int getRandomNum(int number) {
        if (isFirst) {
            isFirst = false;
            return 1;
        }
        return  (int) (Math.random() * number);
    }
}
