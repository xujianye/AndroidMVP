package com.xjy.hyx.mvpretrofitproject.presenters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xjy.hyx.mvpretrofitproject.entites.Article;
import com.xjy.hyx.mvpretrofitproject.interfaces.DataListener;
import com.xjy.hyx.mvpretrofitproject.models.ArticleModel;
import com.xjy.hyx.mvpretrofitproject.models.impl.ArticalModelImpl;
import com.xjy.hyx.mvpretrofitproject.retrofit.RequestCallBack;
import com.xjy.hyx.mvpretrofitproject.retrofit.RetrofitClient;
import com.xjy.hyx.mvpretrofitproject.retrofit.entites.ReqArticle;
import com.xjy.hyx.mvpretrofitproject.ui.interfaces.ArticleViewInterface;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 15:30
 * email：jianyexu@hyx.com
 */
public class ArticlePresenter extends BasePresenter<ArticleViewInterface> {

    ArticleModel mArticleMode = new ArticalModelImpl();

    // 获取文章
    public void fetchArticles() {
        getView().showLoading();
        RetrofitClient.getServerApi().getArticles(new ReqArticle()).enqueue(new RequestCallBack() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                super.onResponse(call, response);

                Gson gson = new Gson();
                List<Article> articles = gson.fromJson(data, new TypeToken<List<Article>>() {
                }.getType());

                getView().hideLoading();
                getView().showArticles(articles);
                mArticleMode.saveArticles(articles);
            }
        });
    }

    public void loadArticlesFromDB() {
        mArticleMode.loadArticlesFromCache(new DataListener<List<Article>>() {
            @Override
            public void onComplete(List<Article> result) {
                getView().showLoading();
            }
        });
    }
}
