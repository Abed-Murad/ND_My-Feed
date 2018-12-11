package com.am.my_feed.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.am.my_feed.article.Article;

@Database(entities = Article.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase INSTANCE;

    public static NoteDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "article_db").build();
        }
        return INSTANCE;
    }

    public static void destroyInstances() {
        INSTANCE = null;
    }

    public abstract NoteModelDao getNoteModelDao();
}
