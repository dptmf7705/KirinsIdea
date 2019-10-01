package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.entity.Bookmark;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class BookmarkRepositoryImpl implements BookmarkRepository {
    private volatile static BookmarkRepository INSTANCE = null;

    public static BookmarkRepository getInstance(@NonNull final BookmarkDao bookmarkDao) {
        if (INSTANCE == null) {
            synchronized (BookmarkRepositoryImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BookmarkRepositoryImpl(bookmarkDao);
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    private final BookmarkDao bookmarkDao;

    private BookmarkRepositoryImpl(@NonNull final BookmarkDao bookmarkDao) {
        this.bookmarkDao = bookmarkDao;
    }

    @NonNull
    @Override
    public Single<Bookmark> observeBookmarkById(int id) {
        return bookmarkDao.selectById(id).subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public DataSource.Factory<Integer, Bookmark> observeBookmarkList() {
        return bookmarkDao.selectAll();
    }

    @NonNull
    @Override
    public Completable observeAddNewBookmark(@NonNull Bookmark bookmark) {
        return bookmarkDao.insert(bookmark).subscribeOn(Schedulers.io());
    }
}
