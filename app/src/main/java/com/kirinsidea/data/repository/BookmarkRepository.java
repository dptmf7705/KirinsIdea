package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.data.source.remote.request.NewBookmarkRequest;
import com.kirinsidea.data.source.remote.response.NewBookmarkResponse;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BookmarkRepository extends BaseRepository {

    @NonNull
    Single<Bookmark> observeBookmarkById(final int id);

    @NonNull
    DataSource.Factory<Integer, Bookmark> observeBookmarkList();

    @NonNull
    Completable observeAddNewBookmark(@NonNull final Bookmark bookmark);

    @NonNull
    Single<NewBookmarkResponse> observeAddNewBookmark(@NonNull NewBookmarkRequest request);
}
