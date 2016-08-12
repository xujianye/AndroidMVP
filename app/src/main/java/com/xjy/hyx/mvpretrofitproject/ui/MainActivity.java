package com.xjy.hyx.mvpretrofitproject.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.adapters.NewsAdapter;
import com.xjy.hyx.mvpretrofitproject.adapters.NewsPageAdapter;
import com.xjy.hyx.mvpretrofitproject.entites.Article;
import com.xjy.hyx.mvpretrofitproject.entites.News;
import com.xjy.hyx.mvpretrofitproject.interfaces.OnItemClickListener;
import com.xjy.hyx.mvpretrofitproject.presenters.NewsPresenter;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.NewsListViewInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends MVPBaseActivity<NewsListViewInterface, NewsPresenter> {

    TabLayout mTabLayout;
    ViewPager mViewPager;
    List<String> mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("新闻头条");
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

        mTitles = new ArrayList<>();
        mTitles.add("头条");
        mTitles.add("社会");
        mTitles.add("国内");
        mTitles.add("国际");
        mTitles.add("娱乐");
        mTitles.add("体育");
        mTitles.add("军事");
        mTitles.add("科技");
        mTitles.add("财经");
        mTitles.add("时尚");

        for (String title : mTitles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(title));
        }

        mViewPager.setAdapter(new NewsPageAdapter(getSupportFragmentManager(), mTitles));
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
