package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.entity.BookmarkEntity;
import com.kirinsidea.data.source.remote.kirin.api.BookmarkApi;
import com.kirinsidea.data.source.remote.kirin.api.FileApi;
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

    private BookmarkDao bookmarkLocalDataSource;
    private BookmarkApi bookmarkRemoteDataSource;
    private FileApi fileRemoteDataSource;

    @NonNull
    @Override
    public BaseRepository init(@NonNull final Object... dataSources) {
        this.bookmarkLocalDataSource = (BookmarkDao) dataSources[0];
        this.bookmarkRemoteDataSource = (BookmarkApi) dataSources[1];
        this.fileRemoteDataSource = (FileApi) dataSources[2];
        return this;
    }

    @NonNull
    @Override
    public Single<Bookmark> observeBookmarkById(final int id) {
        return bookmarkLocalDataSource.selectById(id)
                .map(entity -> new Bookmark.Builder()
                        .fromEntity(entity)
                        .setContents("")
                        .build())
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public DataSource.Factory<Integer, BookmarkItem> observeBookmarkList() {
        return bookmarkLocalDataSource.selectAll().map(entity ->
                new BookmarkItem.Builder().fromEntity(entity).build());
    }

    @NonNull
    @Override
    public Single<Integer> checkIfExistUrl(String Url) {
        return bookmarkLocalDataSource.selectByUrl(Url);
    }

    @NonNull
    @Override
    public Completable observeAddNewBookmark(@NonNull NewBookmarkRequest request) {
        return bookmarkRemoteDataSource.addNewBookmark(request)
                .flatMapCompletable(response ->
                        fileRemoteDataSource.downloadFileByUrl(response.getHtml())
                                .map(responseBody -> FileUtil.writeFile(responseBody.source()))
                                .flatMapCompletable(path -> bookmarkLocalDataSource
                                        .insert(new BookmarkEntity.Builder(response)
                                                .setPath(path)
                                                .build())))
                .subscribeOn(Schedulers.io());

    }
}
