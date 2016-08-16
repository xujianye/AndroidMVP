package com.xjy.hyx.mvpretrofitproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.xjy.hyx.mvpretrofitproject.ui.Constants;
import com.xjy.hyx.mvpretrofitproject.ui.WebActivity;
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

    RecyclerView mRecyclerView;
    List<News> mNewsList = new LinkedList<>();
    NewsAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    private String type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.layout_recycler_view, null);
            initViews();
            int position = getArguments().getInt("position");
            type = Constants.NEWS[position][0];
            mPresenter.fetchNews(type);
        }
        return mRootView;
    }

    public void initViews() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NewsAdapter(mNewsList);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchNews(type);
            }
        });

        mAdapter.setOnItemClickListener(new OnItemClickListener<News>() {
            @Override
            public void onClick(News item) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("url", item.url);
                intent.putExtra("title", "新闻详情");
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
