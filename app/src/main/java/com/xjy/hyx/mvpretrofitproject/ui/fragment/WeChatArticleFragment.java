package com.xjy.hyx.mvpretrofitproject.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.adapters.WeChatArticleAdapter;
import com.xjy.hyx.mvpretrofitproject.entites.WeChatArticle;
import com.xjy.hyx.mvpretrofitproject.interfaces.OnItemClickListener;
import com.xjy.hyx.mvpretrofitproject.presenters.WeChatPresenter;
import com.xjy.hyx.mvpretrofitproject.ui.WebActivity;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.WeChatViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 12:24
 * email：jianyexu@hyx.com
 */
public class WeChatArticleFragment extends MVPBaseFragment<WeChatViewInterface, WeChatPresenter> implements WeChatViewInterface {

    RecyclerView mRecyclerView;
    WeChatArticleAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    List<WeChatArticle> mWeChatArticles = new ArrayList<>();
    private static int page = 1;

    @Override
    protected WeChatPresenter createPresenter() {
        return new WeChatPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.layout_recycler_view, null);
            initViews();
            mPresenter.fetchArticles(page);
        }
        return mRootView;
    }

    public void initViews() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new WeChatArticleAdapter(mWeChatArticles);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.fetchArticles(page);
            }
        });

        mAdapter.setOnItemClickListener(new OnItemClickListener<WeChatArticle>() {
            @Override
            public void onClick(WeChatArticle item) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("url", item.url);
                intent.putExtra("title", "微信精选");
                startActivity(intent);
            }
        });
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

    @Override
    public void showArticles(List<WeChatArticle> weChatArticles) {
        mWeChatArticles.clear();
        mWeChatArticles.addAll(weChatArticles);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String errMsg) {
        Snackbar.make(mRecyclerView, errMsg, Snackbar.LENGTH_SHORT).show();
    }
}
