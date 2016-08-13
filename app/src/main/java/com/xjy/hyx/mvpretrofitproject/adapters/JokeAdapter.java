package com.xjy.hyx.mvpretrofitproject.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xjy.hyx.mvpretrofitproject.R;
import com.xjy.hyx.mvpretrofitproject.retrofit.entites.Joke;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/13 0013 16:41
 * email：jianyexu@hyx.com
 */
public class JokeAdapter extends BaseAdapter<Joke, RecyclerView.ViewHolder> {


    public JokeAdapter(List<Joke> dataSet) {
        super(dataSet);
    }

    @Override
    protected void bindDataToItemView(RecyclerView.ViewHolder viewHolder, Joke item, int position) {

        if (viewHolder instanceof JokeViewHolder) {
            JokeViewHolder jokeViewHolder = (JokeViewHolder) viewHolder;
            jokeViewHolder.mContent.setText((position + 1) + ". " + item.content);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JokeViewHolder(inflateItemView(parent, R.layout.item_joke));
    }

    class JokeViewHolder extends RecyclerView.ViewHolder {

        TextView mContent;

        public JokeViewHolder(View itemView) {
            super(itemView);
            mContent = (TextView) itemView.findViewById(R.id.tv_joke);
        }
    }

}
