package com.xjy.hyx.mvpretrofitproject.ui.interfaces;

import android.support.v4.app.Fragment;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 11:08
 * email：jianyexu@hyx.com
 */
public interface MainViewInterface {
    void start();

    void switchFragment(Fragment newFragment, String title);
}
