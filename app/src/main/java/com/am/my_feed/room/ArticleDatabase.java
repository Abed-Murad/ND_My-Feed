package com.am.my_feed.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Article.class, version = 1)
public abstract class ArticleDatabase extends RoomDatabase {

    private static ArticleDatabase INSTANCE;

    public static ArticleDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ArticleDatabase.class, "article_db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstances() {
        INSTANCE = null;
    }

    public abstract ArticleModelDao getArticlesModelDao();
}
