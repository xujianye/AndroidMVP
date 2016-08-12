package com.xjy.hyx.mvpretrofitproject.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.xjy.hyx.mvpretrofitproject.presenters.BasePresenter;

/**
 * description:
 * author：xujianye
 * date: 2016/8/12 0012 10:49
 * email：jianyexu@hyx.com
 */
public abstract class MVPBaseFragment<V, T extends BasePresenter<V>> extends Fragment implements View.OnClickListener {

    protected T mPresenter; // Presenter 对象

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    protected abstract T createPresenter();

    @Override
    public void onClick(View v) {

    }
}
