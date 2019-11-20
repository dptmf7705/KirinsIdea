package com.kirinsidea.data.repository.bookmark;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.App;
import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.error.RoomException;
import com.kirinsidea.data.source.local.room.error.RoomResult;
import com.kirinsidea.data.source.remote.kirin.api.BookmarkApi;
import com.kirinsidea.data.source.remote.kirin.api.FileApi;
import com.kirinsidea.data.source.remote.kirin.error.FileDownloadException;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitException;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitResultCode;
import com.kirinsidea.data.source.remote.kirin.mock.BookmarkMockApi;
import com.kirinsidea.data.source.remote.kirin.mock.FileMockApi;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.ui.bookmark.Bookmark;
import com.kirinsidea.ui.bookmarklist.BookmarkItem;
import com.kirinsidea.ui.folderlist.Folder;

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
        this.bookmarkRemoteDataSource = new BookmarkMockApi();
//        this.bookmarkRemoteDataSource = (BookmarkApi) dataSources[1];
        this.fileRemoteDataSource = new FileMockApi();
//        this.fileRemoteDataSource = (FileApi) dataSources[2];
        return this;
    }

    @NonNull
    @Override
    public Single<Bookmark> observeBookmarkById(final String id) {
        return bookmarkLocalDataSource.selectById(id)
                .subscribeOn(Schedulers.io())
                .map(entity -> new Bookmark.Builder()
                        .fromEntity(entity)
                        .setContents("")
                        .build())
                .switchIfEmpty(Single.error(new RoomException(RoomResult.NULL)));
    }

    @NonNull
    @Override
    public DataSource.Factory<Integer, BookmarkItem> observeBookmarkListById(final String id) {
        if (Folder.ALL_BOOKMARK.getId().equals(id)) {
            return bookmarkLocalDataSource.selectAll().map(entity ->
                    new BookmarkItem.Builder().fromEntity(entity).build());
        } else {
            return bookmarkLocalDataSource.selectByFolderId(id)
                    .map(entity -> new BookmarkItem.Builder().fromEntity(entity).build());
        }
    }

    /**
     * 북마크 추가시 url 중복 검사
     */
    @NonNull
    @Override
    public Single<Integer> checkIfExistUrl(String Url) {
        return bookmarkLocalDataSource.selectByUrl(Url).subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Completable observeAddNewBookmark(@NonNull NewBookmarkRequest request) {
        return bookmarkRemoteDataSource.addNewBookmark(request)
                .subscribeOn(Schedulers.io())
                .flatMapCompletable(response -> {
                    if (response.getRetrofitResultCode() == RetrofitResultCode.SUCCESS) {
                        return fileRemoteDataSource.downloadFileByUrl(response.getResult().getHtml())
                                .subscribeOn(Schedulers.io())
                                .onErrorResumeNext(error -> Single.error(new FileDownloadException(RetrofitResultCode.BOOKMARK_ERROR_407)))
//                                .map(responseBody -> FileUtil.writeFile(responseBody.source()))
                                .map(responseBody -> App.instance().getContext().getExternalFilesDir(null).getAbsolutePath() + "/" + "testt.html")
                                .flatMapCompletable(path -> bookmarkLocalDataSource
                                        .insert(response.getResult()));
                    } else {
                        return Completable.error(new RetrofitException(response.getRetrofitResultCode()));
                    }
                });
    }
}
