package com.xjy.hyx.mvpretrofitproject.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.xjy.hyx.mvpretrofitproject.R;

public class LoadImageAsynTask {

	private ImageLoader mImageLoader;
	DisplayImageOptions options;

	public LoadImageAsynTask() {
		init();
	}

	private void init() {
		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.bg_loadding)
				.showImageOnFail(R.drawable.bg_load_fail).cacheInMemory(true).cacheOnDisc(true).considerExifParams(true)
				.resetViewBeforeLoading(true)
		.build();
		mImageLoader = ImageLoader.getInstance();
	}

	public ImageLoader getImageLoader() {
		return mImageLoader;
	}

	public void loadDrawable(String url, ImageLoadingListener listener) {
		mImageLoader.loadImage(url, listener);
	}

	public void loadDrawable(String url, DisplayImageOptions option, ImageLoadingListener listener) {
		mImageLoader.loadImage(url, option, listener);
	}

	public void loadDrawable(String url, ImageView view, ImageLoadingListener listener) {
		if (listener == null) {
			mImageLoader.displayImage(url, view, options, animateFirstListener_default);
		} else {
			mImageLoader.displayImage(url, view, options, listener);
		}
	}
	
	
	public void loadDrawable(String url, ImageView view) {
		mImageLoader.displayImage(url, view, options, null);
	}

	public void loadDrawable(String url, ImageView view, DisplayImageOptions option, ImageLoadingListener listener) {
		if (option == null) {
			mImageLoader.displayImage(url, view, options, listener);
		} else {
			mImageLoader.displayImage(url, view, option, listener);
		}
	}

	private ImageLoadingListener animateFirstListener_default = new AnimateFirstDisplayListener();

	private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				FadeInBitmapDisplayer.animate(imageView, 500);
			}
		}
	}

}