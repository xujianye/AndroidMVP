package com.xjy.hyx.mvpretrofitproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.adapters.NewsAdapter;
import com.xjy.hyx.mvpretrofitproject.entites.News;
import com.xjy.hyx.mvpretrofitproject.interfaces.OnItemClickListener;
import com.xjy.hyx.mvpretrofitproject.presenters.NewsPresenter;
import com.xjy.hyx.mvpretrofitproject.ui.NewsDetailActivity;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.NewsListViewInterface;

import java.util.LinkedList;
import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/12 0012 17:57
 * email：jianyexu@hyx.com
 */
public class NewsListFragment extends MVPBaseFragment<NewsListViewInterface, NewsPresenter> implements NewsListViewInterface {

    private View mView;
    RecyclerView mRecyclerView;
    List<News> mNewsList = new LinkedList<>();
    NewsAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private String type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_news_list, null);
            initViews();
            type = getArguments().getString("type");
            mPresenter.fetchNews(type);
        }
        return mView;
    }

    public void initViews() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NewsAdapter(mNewsList);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.swipeRefresh);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchNews(type);
            }
        });

        mAdapter.setOnItemClickListener(new OnItemClickListener<News>() {
            @Override
            public void onClick(News item) {
                Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                intent.putExtra("url", item.url);
                startActivity(intent);
            }
        });
    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    public static MVPBaseFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public void showNews(List<News> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        if (!mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 500);
    }

}
