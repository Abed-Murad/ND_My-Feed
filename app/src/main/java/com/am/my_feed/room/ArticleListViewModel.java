package com.am.my_feed.room;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.am.my_feed.article.Article;

import java.util.List;

public class ArticleListViewModel extends AndroidViewModel {
    private final LiveData<List<Article>> articlesList;
    private ArticleDatabase articleDatabase;


    public ArticleListViewModel(@NonNull Application application) {
        super(application);
        articleDatabase = ArticleDatabase.getDatabase(this.getApplication());
        articlesList = articleDatabase.getArticlesModelDao().getAllArticles();
    }

    public LiveData<List<Article>> getArticlesList() {
        return articlesList;
    }

    public void deleteArticles(Article articles) {
        new DeleteAsyncTask(articleDatabase).execute(articles);
    }

    private class DeleteAsyncTask extends AsyncTask<Article, Void, Void> {
        private ArticleDatabase articleDatabase;
        DeleteAsyncTask(ArticleDatabase articleDatabase) {
            this.articleDatabase = articleDatabase;
        }

        @Override
        protected Void doInBackground(Article... articles) {
            articleDatabase.getArticlesModelDao().deleteArticle(articles[0]);
            return null;
        }
    }
}
