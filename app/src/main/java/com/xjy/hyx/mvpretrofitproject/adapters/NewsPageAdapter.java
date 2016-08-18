package com.xjy.hyx.mvpretrofitproject.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xjy.hyx.mvpretrofitproject.ui.Constants;
import com.xjy.hyx.mvpretrofitproject.ui.fragment.NewsListFragment;

/**
 * description:
 * author：xujianye
 * date: 2016/8/12 0012 18:14
 * email：jianyexu@hyx.com
 */
public class NewsPageAdapter extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;

    public NewsPageAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager = fm;
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    private Fragment getFragment(int position) {
        Fragment fragment = mFragmentManager.findFragmentByTag(getPageTitle(position).toString());
        if (fragment == null) {
            fragment = NewsListFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return Constants.NEWS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.NEWS[position][1];
    }
}
