package com.xjy.hyx.mvpretrofitproject.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.adapters.ArticleAdapter;
import com.xjy.hyx.mvpretrofitproject.entites.Article;
import com.xjy.hyx.mvpretrofitproject.interfaces.OnItemClickListener;
import com.xjy.hyx.mvpretrofitproject.presenters.ArticlePresenter;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.ArticleViewInterface;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends MVPBaseActivity<ArticleViewInterface, ArticlePresenter> implements ArticleViewInterface {

    RecyclerView mRecyclerView;
    List<Article> mArticles = new LinkedList<>();
    ArticleAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MVP");
        setSupportActionBar(toolbar);

        initViews();
        mPresenter.fetchArticles();
    }

    @Override
    protected ArticlePresenter createPresenter() {
        return new ArticlePresenter();
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ArticleAdapter(mArticles);
        mRecyclerView.setAdapter(mAdapter);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchArticles();
            }
        });

        mFloatingActionButton.setOnClickListener(this);
        mAdapter.setOnItemClickListener(new OnItemClickListener<Article>() {
            @Override
            public void onClick(Article item) {
                Snackbar.make(mRecyclerView, item.getTitle(), Snackbar.LENGTH_LONG).setAction("哈哈", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 2016/8/12 0012
                    }
                }).show();
            }
        });
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
    public void showArticles(List<Article> articles) {
        mArticles.clear();
        mArticles.addAll(articles);
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
