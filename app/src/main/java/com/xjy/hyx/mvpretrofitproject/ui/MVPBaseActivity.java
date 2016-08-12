package com.xjy.hyx.mvpretrofitproject.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xjy.hyx.mvpretrofitproject.presenters.BasePresenter;

/**
 * description:
 * author：xujianye
 * date: 2016/8/12 0012 10:49
 * email：jianyexu@hyx.com
 */
public abstract class MVPBaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements View.OnClickListener {

    protected T mPresenter; // Presenter 对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();

    @Override
    public void onClick(View v) {

    }
}
