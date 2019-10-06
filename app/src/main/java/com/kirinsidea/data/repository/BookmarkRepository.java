package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.data.source.remote.request.AddNewBookmarkRequest;
import com.kirinsidea.data.source.remote.response.AddNewBookmarkResponse;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BookmarkRepository extends BaseRepository{

    @NonNull
    Single<Bookmark> observeBookmarkById(final int id);

    @NonNull
    DataSource.Factory<Integer, Bookmark> observeBookmarkList();

    @NonNull
    Completable observeAddNewBookmark(@NonNull final Bookmark bookmark);

    @NonNull
    Single<AddNewBookmarkResponse> uploadWeb(@NonNull AddNewBookmarkRequest addNewBookmarkRequest);
}
