package com.xjy.hyx.mvpretrofitproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.entites.News;
import com.xjy.hyx.mvpretrofitproject.entites.WeChatArticle;
import com.xjy.hyx.mvpretrofitproject.utils.LoadImageAsynTask;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/12 0012 16:50
 * email：jianyexu@hyx.com
 */
public class WeChatArticleAdapter extends BaseAdapter<WeChatArticle, RecyclerView.ViewHolder> {

    public WeChatArticleAdapter(List<WeChatArticle> articles) {
        super(articles);
    }

    @Override
    protected void bindDataToItemView(RecyclerView.ViewHolder viewHolder, WeChatArticle item, int position) {
        if (viewHolder instanceof NewsViewHolder) {
            bindNewsToNewsItem((NewsViewHolder)viewHolder, item);
        }
    }

    private void bindNewsToNewsItem(NewsViewHolder viewHolder, WeChatArticle item) {
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvFrom.setText(item.source);
        viewHolder.tvDate.setText("来自：");
        new LoadImageAsynTask().loadDrawable(item.firstImg, viewHolder.ivNewsPhoto);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createNewsViewHolder(parent);
    }

    protected RecyclerView.ViewHolder createNewsViewHolder(ViewGroup parent) {
        return new NewsViewHolder(inflateItemView(parent, R.layout.item_news));
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvDate, tvFrom;
        ImageView ivNewsPhoto;

        public NewsViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);
            tvFrom = (TextView) itemView.findViewById(R.id.tv_from);
            ivNewsPhoto = (ImageView) itemView.findViewById(R.id.iv_news_photo);
        }
    }
}
