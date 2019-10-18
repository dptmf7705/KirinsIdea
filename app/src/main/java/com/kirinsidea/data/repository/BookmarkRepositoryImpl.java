package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.entity.BookmarkEntity;
import com.kirinsidea.data.source.remote.kirin.RetrofitClient;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.ui.bookmark.Bookmark;
import com.kirinsidea.ui.bookmarklist.BookmarkItem;
import com.kirinsidea.utils.FileUtil;

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

    private BookmarkRepositoryImpl() {
    }

    private RetrofitClient retrofit;
    private BookmarkDao bookmarkDao;

    @NonNull
    @Override
    public BaseRepository init(@NonNull final Object... dataSources) {
        this.retrofit = (RetrofitClient) dataSources[0];
        this.bookmarkDao = (BookmarkDao) dataSources[1];
        return this;
    }

    @NonNull
    @Override
    public Single<Bookmark> observeBookmarkById(final int id) {
        return bookmarkDao.selectById(id)
                .map(entity -> new Bookmark.Builder()
                        .fromEntity(entity)
                        .setContents("")
                        .build())
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public DataSource.Factory<Integer, BookmarkItem> observeBookmarkList() {
        return bookmarkDao.selectAll().map(entity -> new BookmarkItem.Builder().fromEntity(entity).build());
    }

    @NonNull
    @Override
    public Single<Integer> checkIfExistUrl(String Url) {
        return bookmarkDao.selectByUrl(Url);
    }

    @NonNull
    @Override
    public Completable observeAddNewBookmark(@NonNull NewBookmarkRequest request) {
        return retrofit.observeAddNewBookmark(request)
                .flatMapCompletable(response ->
                        retrofit.downloadFileByUrl(response.getHtml())
                                .map(responseBody -> FileUtil.writeFile(responseBody.source()))
                                .flatMapCompletable(path -> bookmarkDao
                                        .insert(new BookmarkEntity.Builder()
                                                .fromResponse(response)
                                                .setPath(path)
                                                .build())))
                .subscribeOn(Schedulers.io());

    }
}
