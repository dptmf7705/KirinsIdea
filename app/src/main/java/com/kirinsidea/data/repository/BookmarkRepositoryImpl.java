package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.data.source.remote.RetrofitClient;
import com.kirinsidea.data.source.remote.request.AddNewBookmarkRequest;
import com.kirinsidea.data.source.remote.response.AddNewBookmarkResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class BookmarkRepositoryImpl implements BookmarkRepository {

    private volatile static BookmarkRepository INSTANCE = null;

    public static BookmarkRepository getInstance(@NonNull final RetrofitClient retrofit,
                                                 @NonNull final BookmarkDao bookmarkDao) {
        if (INSTANCE == null) {
            synchronized (BookmarkRepositoryImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BookmarkRepositoryImpl(retrofit, bookmarkDao);
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    private final RetrofitClient retrofit;
    @NonNull
    private final BookmarkDao bookmarkDao;

    private BookmarkRepositoryImpl(@NonNull final RetrofitClient retrofit,
                                   @NonNull final BookmarkDao bookmarkDao) {
        this.retrofit = retrofit;
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

    @NonNull
    @Override
    public Single<AddNewBookmarkResponse> observeAddNewBookmark(@NonNull AddNewBookmarkRequest addNewBookmarkRequest) {
        return retrofit.observeAddNewBookmark(addNewBookmarkRequest).subscribeOn(Schedulers.io());
    }
}
