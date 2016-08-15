package com.xjy.hyx.mvpretrofitproject.presenters;

import android.content.res.Resources;
import android.support.v4.app.Fragment;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.ui.fragment.JokeFragment;
import com.xjy.hyx.mvpretrofitproject.ui.fragment.NewsFragment;
import com.xjy.hyx.mvpretrofitproject.ui.fragment.WeChatArticleFragment;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.MainViewInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 11:09
 * email：jianyexu@hyx.com
 */
public class MainPresenter extends BasePresenter<MainViewInterface> {

    private Map<Integer, Fragment> mFragments = new HashMap<>();
    private Fragment oldFragment;

    public void onStart() {
        getView().start();
    }

    public void switchFragment(int position, Resources resources) {

        Fragment newFragment = mFragments.get(position);
        if (newFragment == null) {
            switch (position) {
                case 0:
                    newFragment = new NewsFragment();
                    break;

                case 1:
                    newFragment = new JokeFragment();
                    break;

                case 2:
                    newFragment = new WeChatArticleFragment();
                    break;
            }
            mFragments.put(position, newFragment);
        }
        String[] array = resources.getStringArray(R.array.left_item);
        String title = null;
        if (position < array.length) {
            title = array[position];
        }
        getView().switchFragment(newFragment, oldFragment, title);
        oldFragment = newFragment;
    }

}
