package com.kirinsidea.data.repository.bookmark;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.ui.bookmark.Bookmark;
import com.kirinsidea.ui.bookmarklist.BookmarkItem;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BookmarkRepository extends BaseRepository {

    @NonNull
    Single<Bookmark> observeBookmarkById(final String id);

    @NonNull
    DataSource.Factory<Integer, BookmarkItem> observeBookmarkListById(final String id);

    @NonNull
    Single<Integer> checkIfExistUrl(String Url);

    @NonNull
    Completable observeAddNewBookmark(@NonNull NewBookmarkRequest request);
}
