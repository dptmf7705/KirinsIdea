package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.ui.bookmark.Bookmark;
import com.kirinsidea.ui.bookmarklist.BookmarkItem;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BookmarkRepository extends BaseRepository {

    @NonNull
    Single<Bookmark> observeBookmarkById(final int id);

    @NonNull
    DataSource.Factory<Integer, BookmarkItem> observeBookmarkList();

    @NonNull
    Single<Integer> checkIfExistUrl(String Url);

    @NonNull
    Completable observeAddNewBookmark(@NonNull NewBookmarkRequest request);
}
