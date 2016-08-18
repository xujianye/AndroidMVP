package com.xjy.hyx.mvpretrofitproject.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.presenters.MainPresenter;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.MainViewInterface;

import java.util.List;

public class MainActivity extends MVPBaseActivity<MainViewInterface, MainPresenter> implements MainViewInterface {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private ListView mLeftList;
    private FragmentManager mFragmentManager;
    private long lastTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.onStart(getResources().getStringArray(R.array.left_item));
        initViews();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    private void initViews() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mLeftList = (ListView) findViewById(R.id.left_list);
        mPresenter.switchFragment(0, mFragmentManager);

        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mLeftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPresenter.switchFragment(position, mFragmentManager);
                mDrawerLayout.closeDrawers();
            }
        });
    }

    @Override
    public void start() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(mPresenter.getTitle(0));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    public void setTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - lastTime < 2000) {
            finish();
        } else {
            lastTime = System.currentTimeMillis();
            Snackbar.make(mLeftList, "双击退出程序", Snackbar.LENGTH_SHORT).show();
        }
    }
}
