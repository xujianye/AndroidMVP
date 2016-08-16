package com.xjy.hyx.mvpretrofitproject.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.adapters.NewsPageAdapter;
import com.xjy.hyx.mvpretrofitproject.presenters.BasePresenter;
import com.xjy.hyx.mvpretrofitproject.ui.Constants;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 10:33
 * email：jianyexu@hyx.com
 */
public class NewsFragment extends Fragment {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.layout_news_list, null);
            initViews();
        }
        return mRootView;
    }

    private void initViews() {
        mTabLayout = (TabLayout) mRootView.findViewById(R.id.tabs);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.viewpager);

        for (int i = 0; i < Constants.NEWS.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(Constants.NEWS[i][1]));
        }

        mViewPager.setAdapter(new NewsPageAdapter(getActivity().getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }
}
