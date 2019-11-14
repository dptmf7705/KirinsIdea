package com.kirinsidea.data.repository.bookmark;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.App;
import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.entity.BookmarkEntity;
import com.kirinsidea.data.source.remote.kirin.api.BookmarkApi;
import com.kirinsidea.data.source.remote.kirin.api.FileApi;
import com.kirinsidea.data.source.remote.kirin.mock.BookmarkMockApi;
import com.kirinsidea.data.source.remote.kirin.mock.FileMockApi;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.ui.bookmark.Bookmark;
import com.kirinsidea.ui.bookmarklist.BookmarkItem;

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
    public DataSource.Factory<Integer, BookmarkItem> observeBookmarkList(final int id) {
        if(id == ALL_BOOKMARK){
            return bookmarkLocalDataSource.selectAll().map(entity ->
                    new BookmarkItem.Builder().fromEntity(entity).build());
        }else{
            return bookmarkLocalDataSource.selectByFolderId(id)
                    .map(entity -> new BookmarkItem.Builder().fromEntity(entity).build());
        }

    }

    @NonNull
    @Override
    public Single<Integer> checkIfExistUrl(String Url) {
        return bookmarkLocalDataSource.selectByUrl(Url).subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Completable observeAddNewBookmark(@NonNull NewBookmarkRequest request) {
        return bookmarkRemoteDataSource.addNewBookmark(request)
                .flatMapCompletable(response ->
                        fileRemoteDataSource.downloadFileByUrl(response.getHtml())
//                                .map(responseBody -> FileUtil.writeFile(responseBody.source()))
                                .map(responseBody -> App.instance().getContext().getExternalFilesDir(null).getAbsolutePath() + "/" + "testt.html")
                                .flatMapCompletable(path -> bookmarkLocalDataSource
                                        .insert(new BookmarkEntity.Builder(response)
                                                .setPath(path)
                                                .build())))
                .subscribeOn(Schedulers.io());

    }

    @Override
    public Single<Integer> observeBookmarkByFavorite() {
        if(bookmarkLocalDataSource.selectByFavorite()==null){
            return null;
        }else {
            return bookmarkLocalDataSource.selectByFavorite().subscribeOn(Schedulers.io());
        }
    }
}
