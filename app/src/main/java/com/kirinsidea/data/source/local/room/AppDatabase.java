package com.kirinsidea.data.source.local.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.data.source.local.room.entity.Folder;

@Database(entities = {Bookmark.class, Folder.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
abstract public class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(@NonNull final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, Constants.Room.DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract BookmarkDao bookmarkDao();
    public abstract FolderDao folderDao();
}