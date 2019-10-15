package com.kirinsidea.data.source.local.room;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.kirinsidea.App;
import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.local.room.dao.MemoDao;
import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.data.source.local.room.entity.Folder;
import com.kirinsidea.data.source.local.room.entity.Memo;

@Database(entities = {Bookmark.class, Folder.class, Memo.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static class LazyHolder {
        private static final AppDatabase INSTANCE = Room.databaseBuilder(
                App.instance().getContext(),
                AppDatabase.class, Constants.Room.DATABASE_NAME)
                .build();
    }

    public static AppDatabase getDatabase() {
        return LazyHolder.INSTANCE;
    }

    public abstract BookmarkDao bookmarkDao();

    public abstract FolderDao folderDao();

    public abstract MemoDao memoDao();
}