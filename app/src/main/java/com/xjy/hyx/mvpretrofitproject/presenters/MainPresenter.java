package com.xjy.hyx.mvpretrofitproject.presenters;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.ui.fragment.JokeFragment;
import com.xjy.hyx.mvpretrofitproject.ui.fragment.NewsFragment;
import com.xjy.hyx.mvpretrofitproject.ui.fragment.WeChatArticleFragment;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.MainViewInterface;

/**
 * description:
 * author：xujianye
 * date: 2016/8/15 0015 11:09
 * email：jianyexu@hyx.com
 */
public class MainPresenter extends BasePresenter<MainViewInterface> {

    /**
     * fragments title
     */
    private String[] mTitles;

    /**
     * 初始化
     * @param titles
     */
    public void onStart(String[] titles) {
        mTitles = titles;
        getView().start();
    }

    /**
     * 获取Fragment
     * @param position 位置
     * @param fragmentManager
     * @return
     */
    private Fragment getFragment(int position, FragmentManager fragmentManager) {

        String title = getTitle(position);
        if (TextUtils.isEmpty(title)) {
            return null;
        }
        Fragment fragment = fragmentManager.findFragmentByTag(title);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new NewsFragment();
                    break;

                case 1:
                    fragment = new JokeFragment();
                    break;

                case 2:
                    fragment = new WeChatArticleFragment();
                    break;
            }
        }
        return fragment;
    }

    /**
     * 切换Fragment
     * @param position 位置
     * @param fragmentManager
     */
    public void switchFragment(int position, FragmentManager fragmentManager) {

        Fragment newFragment = getFragment(position, fragmentManager);
        String title = getTitle(position);
        if (newFragment != null && !TextUtils.isEmpty(title)) {

            FragmentTransaction mFragmentTransaction = fragmentManager.beginTransaction();
            // 隐藏其他的Fragment
            for (String string : mTitles) {
                if (!TextUtils.equals(string, title)) {
                    Fragment fragment = fragmentManager.findFragmentByTag(string);
                    if (fragment != null) {
                        mFragmentTransaction.hide(fragment);
                    }
                }
            }

            //已添加 显示Fragment，否则添加进去
            if (newFragment.isAdded()) {
                mFragmentTransaction.show(newFragment);
            } else {
                mFragmentTransaction.add(R.id.frame_content, newFragment, title);
                mFragmentTransaction.addToBackStack(title);
            }
            mFragmentTransaction.commit();
            // 回调设置toolbar标题
            getView().setTitle(title);
        }
    }

    /**
     * 获取对应位置的Fragment title
     * @param position 位置
     * @return
     */
    public String getTitle(int position) {
        String title = null;
        if (position < mTitles.length) {
            title = mTitles[position];
        }
        return title;
    }

}
