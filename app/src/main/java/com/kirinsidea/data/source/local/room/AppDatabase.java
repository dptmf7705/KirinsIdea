package com.kirinsidea.data.source.local.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.local.room.dao.HighlightDao;
import com.kirinsidea.data.source.local.room.entity.BookmarkEntity;
import com.kirinsidea.data.source.local.room.entity.FolderEntity;
import com.kirinsidea.data.source.local.room.entity.HighlightEntity;
import com.kirinsidea.extension.room.Converters;

@TypeConverters({Converters.class})
@Database(entities = {
        BookmarkEntity.class,
        FolderEntity.class,
        HighlightEntity.class
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
        }

        throw new IllegalArgumentException("Unknown Dao class" + daoClass.getSimpleName());
    }

    abstract BookmarkDao bookmarkDao();

    abstract FolderDao folderDao();

    abstract HighlightDao highlightDao();
}