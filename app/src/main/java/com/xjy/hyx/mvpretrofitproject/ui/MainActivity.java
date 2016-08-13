package com.xjy.hyx.mvpretrofitproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.adapters.NewsPageAdapter;
import com.xjy.hyx.mvpretrofitproject.presenters.NewsPresenter;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.NewsListViewInterface;

public class MainActivity extends MVPBaseActivity<NewsListViewInterface, NewsPresenter> {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    TextView mTvJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    private void initViews() {
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTvJoke = (TextView) findViewById(R.id.tv_joke);

        for (int i = 0; i < Constants.NEWS.length; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(Constants.NEWS[i][1]));
        }

        mViewPager.setAdapter(new NewsPageAdapter(getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        mTvJoke.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_joke:
                Intent intent = new Intent(this, JokeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
