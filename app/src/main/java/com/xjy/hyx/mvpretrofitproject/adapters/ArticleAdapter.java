package com.xjy.hyx.mvpretrofitproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.entites.Article;
import com.xjy.hyx.mvpretrofitproject.utils.LoadImageAsynTask;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 16:32
 * email：jianyexu@hyx.com
 */
public class ArticleAdapter extends BaseAdapter<Article, RecyclerView.ViewHolder> {

    public ArticleAdapter(List<Article> articles){
        super(articles);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createArticleViewHolder(parent);
    }

    protected RecyclerView.ViewHolder createArticleViewHolder(ViewGroup parent) {
        return new ViewHolder(inflateItemView(parent, R.layout.item_subject));
    }

    @Override
    protected void bindDataToItemView(RecyclerView.ViewHolder holder, Article article) {
        if (holder instanceof ViewHolder) {
            bindArticleToItemView((ViewHolder) holder, article);
        }
    }

    protected void bindArticleToItemView(ViewHolder holder, Article article) {
        holder.tvSubject.setText(article.getLabel());
        holder.tvSubjectName.setText(article.getTitle());
        holder.tvActiveCount.setText(article.getActiveCount());
        holder.tvUserCount.setText(article.getPeopleCount() + "人");
        new LoadImageAsynTask().loadDrawable(article.getImg(), holder.ivSubjectBg);

        if ("1".equals(article.getIsHolding()) && "0".equals(article.getSubjectType())) {
            holder.tvSubject.setVisibility(View.VISIBLE);
            holder.ivRectangle.setVisibility(View.VISIBLE);
            holder.tvActiveState.setText("场活动进行中");
        } else if ("1".equals(article.getIsHolding()) && "1".equals(article.getSubjectType())) {
            holder.ivRectangle.setVisibility(View.INVISIBLE);
            holder.tvActiveState.setText("进行中");
            holder.tvSubject.setVisibility(View.INVISIBLE);
        } else if ("0".equals(article.getIsHolding()) && "0".equals(article.getSubjectType())) {
            holder.tvActiveState.setText("场活动已结束");
            holder.tvSubject.setVisibility(View.VISIBLE);
            holder.ivRectangle.setVisibility(View.VISIBLE);
        } else {
            holder.tvActiveState.setText("已结束");
            holder.ivRectangle.setVisibility(View.INVISIBLE);
            holder.tvSubject.setVisibility(View.INVISIBLE);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvSubject; // ...专场
        ImageView ivSubjectBg; // 专场海报图
        TextView tvActiveCount; // 活动数量
        TextView tvUserCount; // 人气数
        TextView tvSubjectName; // 专场名称
        TextView tvActiveState; // 专场名称
        ImageView ivRectangle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivRectangle = (ImageView) itemView.findViewById(R.id.iv_rectangle);
            tvSubject = (TextView) itemView.findViewById(R.id.tv_subject);
            tvActiveState = (TextView) itemView.findViewById(R.id.tv_subject_state);
            tvActiveCount = (TextView) itemView.findViewById(R.id.tv_active_count);
            tvUserCount = (TextView) itemView.findViewById(R.id.tv_user_count);
            tvSubjectName = (TextView) itemView.findViewById(R.id.tv_subject_name);
            ivSubjectBg = (ImageView) itemView.findViewById(R.id.iv_subject_bg);
        }
    }
}
