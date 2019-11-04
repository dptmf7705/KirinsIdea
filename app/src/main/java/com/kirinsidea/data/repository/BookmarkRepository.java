package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.paging.DataSource;

import com.kirinsidea.data.source.local.room.entity.FolderEntity;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.ui.bookmark.Bookmark;
import com.kirinsidea.ui.bookmarklist.BookmarkItem;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BookmarkRepository extends BaseRepository {

    @NonNull
    Single<Bookmark> observeBookmarkById(final int id);

    @NonNull
    DataSource.Factory<Integer, BookmarkItem> observeBookmarkList(final int id);

    @NonNull
    Single<Integer> checkIfExistUrl(String Url);

    @NonNull
    Completable observeAddNewBookmark(@NonNull NewBookmarkRequest request);

    int allBookmark = -1;
}
