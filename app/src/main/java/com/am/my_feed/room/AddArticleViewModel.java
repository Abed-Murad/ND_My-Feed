package com.am.my_feed.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.am.my_feed.article.Article;

public class AddArticleViewModel extends AndroidViewModel {
    private ArticleDatabase articleDatabase;

    public AddArticleViewModel(@NonNull Application application) {
        super(application);
        articleDatabase = ArticleDatabase.getDatabase(this.getApplication());
    }

    public void addArticle(Article article) {

        new AddAsyncTask(articleDatabase).execute(article);
    }

    private class AddAsyncTask extends AsyncTask<Article, Void, Void> {
        private ArticleDatabase articleDatabase;

        public AddAsyncTask(ArticleDatabase articleDatabase) {
            this.articleDatabase = articleDatabase;
        }
        @Override
        protected Void doInBackground(Article... articles) {
            articleDatabase.getArticlesModelDao().insertArticle(articles[0]);
            return null;
        }
    }
}
