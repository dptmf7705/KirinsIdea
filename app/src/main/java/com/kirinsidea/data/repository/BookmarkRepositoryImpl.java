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

    public static BookmarkRepository getInstance(@NonNull final BookmarkDao bookmarkDao,
                                                 @NonNull final RetrofitClient client) {
        if (INSTANCE == null) {
            synchronized (BookmarkRepositoryImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BookmarkRepositoryImpl(bookmarkDao, client);
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    private final BookmarkDao bookmarkDao;
    @NonNull
    private RetrofitClient client;


    private BookmarkRepositoryImpl(@NonNull final BookmarkDao bookmarkDao,
                                   @NonNull final RetrofitClient client) {
        this.bookmarkDao = bookmarkDao;
        this.client = client;
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
    public  Single<AddNewBookmarkResponse> uploadWeb(@NonNull AddNewBookmarkRequest addNewBookmarkRequest){
        return client.getContentFromUrl(addNewBookmarkRequest).subscribeOn(Schedulers.io());
    }
}
