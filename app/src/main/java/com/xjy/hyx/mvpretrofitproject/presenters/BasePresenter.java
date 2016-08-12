package com.xjy.hyx.mvpretrofitproject.presenters;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * description:
 * author：xujianye
 * date: 2016/8/12 0012 10:45
 * email：jianyexu@hyx.com
 */
public abstract class BasePresenter<T> {

    protected Reference<T> mViewRef;

    public void attachView(T view) {
        mViewRef = new WeakReference<>(view);
    }

    protected T getView() {
        return mViewRef.get();
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
