package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.data.source.remote.RetrofitClient;
import com.kirinsidea.data.source.remote.request.NewBookmarkRequest;
import com.kirinsidea.data.source.remote.response.NewBookmarkResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class BookmarkRepositoryImpl implements BookmarkRepository {
    private static class LazyHolder {
        private static final BookmarkRepository INSTANCE = new BookmarkRepositoryImpl();
    }

    @NonNull
    public static BookmarkRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private BookmarkRepositoryImpl() { }

    private RetrofitClient retrofit;
    private BookmarkDao bookmarkDao;

    @NonNull
    @Override
    public BaseRepository init(@NonNull Object... dataSources) {
        this.retrofit = (RetrofitClient) dataSources[0];
        this.bookmarkDao = (BookmarkDao) dataSources[1];
        return this;
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
    public Single<NewBookmarkResponse> observeAddNewBookmark(@NonNull NewBookmarkRequest request) {
        return retrofit.observeAddNewBookmark(request).subscribeOn(Schedulers.io());
    }
}
