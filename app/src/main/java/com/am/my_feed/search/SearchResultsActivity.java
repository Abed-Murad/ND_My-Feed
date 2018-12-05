package com.am.my_feed.search;

import android.app.SearchManager;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.am.my_feed.R;
import com.am.my_feed.article.Article;
import com.am.my_feed.databinding.ActivitySearchResultsBinding;
import com.am.my_feed.feed.FeedAdapter;
import com.orhanobut.logger.Logger;

public class SearchResultsActivity extends AppCompatActivity {
    private ActivitySearchResultsBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search_results);
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Logger.d(query);
        }
        mBinding.searchArticlesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.searchArticlesRecyclerView.setAdapter(new FeedAdapter(this, new FeedAdapter.OnArticleClickListener() {
            @Override
            public void onItemClick(View view, int position, Article model) {

            }

            @Override
            public void onBookmarkButtonClick() {

            }
        }));
    }

}
