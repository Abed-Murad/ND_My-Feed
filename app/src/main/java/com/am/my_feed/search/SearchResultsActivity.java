package com.am.my_feed.search;

import android.app.SearchManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.am.my_feed.R;
import com.am.my_feed.room.Article;
import com.am.my_feed.article.ArticleList;
import com.am.my_feed.databinding.ActivitySearchResultsBinding;
import com.am.my_feed.feed.FeedAdapter;
import com.am.my_feed.util.BaseActivity;
import com.am.my_feed.util.FUNC;
import com.orhanobut.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO: Make the layout to the other side when click home arrow at toolbar
public class SearchResultsActivity extends BaseActivity {
    private ActivitySearchResultsBinding mBinding;
    private FeedAdapter mFeedAdapter;
    private String mQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_results);
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
             mQuery = intent.getStringExtra(SearchManager.QUERY);
            Logger.d(mQuery);
        }
        mBinding.searchArticlesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.searchArticlesRecyclerView.setAdapter(new FeedAdapter(this, new FeedAdapter.OnArticleClickListener() {
            @Override
            public void onItemClick(View view, int position, Article model) {
                FUNC.openUrlInChromeCustomTab(SearchResultsActivity.this, model, model.getUrl());

            }
            @Override
            public void onBookmarkButtonClick() {

            }
        }));



        mBinding.searchArticlesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.searchArticlesRecyclerView.setHasFixedSize(true);
        mFeedAdapter = new FeedAdapter(this, new FeedAdapter.OnArticleClickListener() {
            @Override
            public void onItemClick(View view, int position, Article article) {
                FUNC.openUrlInChromeCustomTab(SearchResultsActivity.this, article, article.getUrl());
            }

            @Override
            public void onBookmarkButtonClick() {

            }
        });


        apiService.getSearch(mQuery , "en").enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                Logger.d(response);
                mFeedAdapter.addAll(response.body().getArticles());
                mBinding.searchArticlesRecyclerView.setAdapter(mFeedAdapter);

            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {

            }
        });


    }

}
