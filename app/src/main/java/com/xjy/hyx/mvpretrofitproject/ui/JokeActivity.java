package com.xjy.hyx.mvpretrofitproject.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.adapters.JokeAdapter;
import com.xjy.hyx.mvpretrofitproject.presenters.JokePresenter;
import com.xjy.hyx.mvpretrofitproject.retrofit.entites.Joke;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.JokeViewInterface;

import java.util.LinkedList;
import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/13 0013 16:32
 * email：jianyexu@hyx.com
 */
public class JokeActivity extends MVPBaseActivity<JokeViewInterface, JokePresenter> implements JokeViewInterface {

    private static int page = 1;
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    JokeAdapter mAdapter;
    List<Joke> mJokes = new LinkedList<>();
    ProgressBar mProgressBar;
    boolean isLoading;
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("开怀一笑");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();
        mPresenter.fetchJokes(page);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new JokeAdapter(mJokes);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setProgressViewOffset(true, 200, 400);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.fetchJokes(page);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                    if (!isLoading) {
                        isLoading = true;
                        mProgressBar.setVisibility(View.VISIBLE);
                        page++;
                        mPresenter.fetchJokes(page);
                    }
                }
            }
        });

        mFloatingActionButton.setOnClickListener(this);
    }

    @Override
    protected JokePresenter createPresenter() {
        return new JokePresenter();
    }

    @Override
    public void showLoading() {
        if (!mSwipeRefreshLayout.isRefreshing() && page == 1) {
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
        if (page == 1) {
            mJokes.clear();
        }
        mJokes.addAll(jokes);
        mAdapter.notifyDataSetChanged();
    }
}
