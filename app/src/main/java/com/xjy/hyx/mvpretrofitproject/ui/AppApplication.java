package com.xjy.hyx.mvpretrofitproject.ui;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 18:00
 * email：jianyexu@hyx.com
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initImageLoader(this);
    }

    public void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "/mvp/cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).threadPoolSize(10)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)).discCache(new UnlimitedDiscCache(cacheDir))
                .discCacheSize(40 * 1024 * 1024).discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs().build();

        ImageLoader.getInstance().init(config);
    }
}
