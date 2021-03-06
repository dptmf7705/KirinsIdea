package com.kirinsidea.data.source.local.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.kirinsidea.data.source.entity.FolderEntity;
import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.local.room.dao.HighlightDao;
import com.kirinsidea.data.source.local.room.dao.MemoDao;
import com.kirinsidea.data.source.entity.BookmarkEntity;
import com.kirinsidea.data.source.entity.HighlightEntity;
import com.kirinsidea.data.source.entity.MemoEntity;

@Database(entities = {
        BookmarkEntity.class,
        FolderEntity.class,
        HighlightEntity.class,
        MemoEntity.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "app_database";

    private static volatile AppDatabase INSTANCE = null;

    public static AppDatabase getInstance(@NonNull final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public <T> T create(@NonNull final Class<T> daoClass) {
        if (daoClass.isAssignableFrom(BookmarkDao.class)) {
            //noinspection unchecked
            return (T) bookmarkDao();
        } else if (daoClass.isAssignableFrom(FolderDao.class)) {
            //noinspection unchecked
            return (T) folderDao();
        } else if (daoClass.isAssignableFrom(HighlightDao.class)) {
            //noinspection unchecked
            return (T) highlightDao();
        } else if (daoClass.isAssignableFrom(MemoDao.class)) {
            //noinspection unchecked
            return (T) memoDao();
        }

        throw new IllegalArgumentException("Unknown Dao class" + daoClass.getSimpleName());
    }

    abstract BookmarkDao bookmarkDao();

    abstract FolderDao folderDao();

    abstract HighlightDao highlightDao();

    abstract MemoDao memoDao();
}