package com.xjy.hyx.mvpretrofitproject.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.adapters.NewsPageAdapter;
import com.xjy.hyx.mvpretrofitproject.ui.Constants;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 10:33
 * email：jianyexu@hyx.com
 */
public class NewsFragment extends Fragment {

    private View mView;
    TabLayout mTabLayout;
    ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.layout_news_list, null);
            initViews();
        }
        return mView;
    }

    private void initViews() {
        mTabLayout = (TabLayout) mView.findViewById(R.id.tabs);
        mViewPager = (ViewPager) mView.findViewById(R.id.viewpager);

        for (int i = 0; i < Constants.NEWS.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(Constants.NEWS[i][1]));
        }

        mViewPager.setAdapter(new NewsPageAdapter(getActivity().getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
