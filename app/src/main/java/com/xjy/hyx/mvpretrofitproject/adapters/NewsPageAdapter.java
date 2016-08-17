package com.xjy.hyx.mvpretrofitproject.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xjy.hyx.mvpretrofitproject.ui.Constants;
import com.xjy.hyx.mvpretrofitproject.ui.fragment.MVPBaseFragment;
import com.xjy.hyx.mvpretrofitproject.ui.fragment.NewsListFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * author：xujianye
 * date: 2016/8/12 0012 18:14
 * email：jianyexu@hyx.com
 */
public class NewsPageAdapter extends FragmentPagerAdapter {

    public NewsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    private Map<Integer, MVPBaseFragment> mFragmentMaps = new HashMap<>();

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    private MVPBaseFragment getFragment(int position) {
        MVPBaseFragment mvpBaseFragment = mFragmentMaps.get(position);
        if (mvpBaseFragment == null) {
            mvpBaseFragment = NewsListFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            mvpBaseFragment.setArguments(bundle);
        }
        return mvpBaseFragment;
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
