package com.xjy.hyx.mvpretrofitproject.ui.fragment;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 14:43
 * email：jianyexu@hyx.com
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.adapters.JokeAdapter;
import com.xjy.hyx.mvpretrofitproject.presenters.JokePresenter;
import com.xjy.hyx.mvpretrofitproject.entites.Joke;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.JokeViewInterface;

import java.util.LinkedList;
import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/13 0013 16:32
 * email：jianyexu@hyx.com
 */
public class JokeFragment extends MVPBaseFragment<JokeViewInterface, JokePresenter> implements JokeViewInterface {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    JokeAdapter mAdapter;
    List<Joke> mJokes = new LinkedList<>();
    ProgressBar mProgressBar;
    boolean isLoading;
    FloatingActionButton mFloatingActionButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.activity_joke, null);
            initViews();
            mPresenter.fetchJokes();
        }
        return mRootView;
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new JokeAdapter(mJokes);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setProgressViewOffset(true, 20, 100);
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.progressBar);
        mFloatingActionButton = (FloatingActionButton) mRootView.findViewById(R.id.floatingActionButton);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchJokes();
            }
        });

//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
//                    if (!isLoading) {
//                        isLoading = true;
//                        mProgressBar.setVisibility(View.VISIBLE);
//                        page++;
//                        mPresenter.fetchJokes(page);
//                    }
//                }
//            }
//        });

        mFloatingActionButton.setOnClickListener(this);
    }

    @Override
    protected JokePresenter createPresenter() {
        return new JokePresenter();
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingActionButton:
                mRecyclerView.getLayoutManager().scrollToPosition(0);
                mPresenter.fetchJokes();
                break;
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
        mProgressBar.setVisibility(View.GONE);
        isLoading = false;
    }

    @Override
    public void showJokes(List<Joke> jokes) {
        mJokes.clear();
        mJokes.addAll(jokes);
        mAdapter.notifyDataSetChanged();
    }
}
