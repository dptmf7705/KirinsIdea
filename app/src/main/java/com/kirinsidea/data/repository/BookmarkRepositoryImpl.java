package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.data.source.local.room.entity.Folder;
import com.kirinsidea.data.source.remote.RetrofitClient;
import com.kirinsidea.data.source.remote.request.WebClippingRequest;
import com.kirinsidea.data.source.remote.response.WebClippingResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class BookmarkRepositoryImpl implements BookmarkRepository {

    private volatile static BookmarkRepository INSTANCE = null;

    public static BookmarkRepository getInstance(@NonNull final BookmarkDao bookmarkDao,
                                                 @NonNull final RetrofitClient client, @NonNull final FolderDao folderDao) {
        if (INSTANCE == null) {
            synchronized (BookmarkRepositoryImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BookmarkRepositoryImpl(bookmarkDao, client, folderDao);
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    private final BookmarkDao bookmarkDao;
    @NonNull
    private RetrofitClient client;
    @NonNull
    private final FolderDao folderDao;

    private BookmarkRepositoryImpl(@NonNull final BookmarkDao bookmarkDao,
                                   @NonNull final RetrofitClient client, @NonNull FolderDao folderDao) {
        this.bookmarkDao = bookmarkDao;
        this.client = client;
        this.folderDao = folderDao;
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
    public  Single<WebClippingResponse> uploadWeb(@NonNull WebClippingRequest webClippingRequest){
        return client.getContentFromUrl(webClippingRequest).subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<Folder> observeFolderById(int id) {
        return folderDao.selectById(id).subscribeOn(Schedulers.io());
    }
}
