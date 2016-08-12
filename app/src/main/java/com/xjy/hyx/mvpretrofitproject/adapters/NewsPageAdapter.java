package com.xjy.hyx.mvpretrofitproject.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xjy.hyx.mvpretrofitproject.ui.Constants;
import com.xjy.hyx.mvpretrofitproject.ui.fragment.MVPBaseFragment;
import com.xjy.hyx.mvpretrofitproject.ui.fragment.NewsListFragment;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/12 0012 18:14
 * email：jianyexu@hyx.com
 */
public class NewsPageAdapter extends FragmentPagerAdapter {

    protected List<String> mTitles;

    public NewsPageAdapter(FragmentManager fm, List<String> titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {

        MVPBaseFragment fragment = NewsListFragment.newInstance();
        String type = "";

        switch (position) {
            case 0:
                type = Constants.NEWS_TOP;
                break;
            case 1:
                type = Constants.NEWS_SHEHUI;
                break;
            case 2:
                type = Constants.NEWS_GUONEI;
                break;
            case 3:
                type = Constants.NEWS_GUOJI;
                break;
            case 4:
                type = Constants.NEWS_YULE;
                break;
            case 5:
                type = Constants.NEWS_TIYU;
                break;
            case 6:
                type = Constants.NEWS_JUNSHI;
                break;
            case 7:
                type = Constants.NEWS_KEJI;
                break;
            case 8:
                type = Constants.NEWS_CAIJING;
                break;
            case 9:
                type = Constants.NEWS_SHISHANG;
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
