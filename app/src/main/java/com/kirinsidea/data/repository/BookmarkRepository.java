package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;

import com.kirinsidea.data.source.local.room.entity.BookmarkEntity;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BookmarkRepository extends BaseRepository {

    @NonNull
    Single<BookmarkEntity> observeBookmarkById(final int id);

    @NonNull
    DataSource.Factory<Integer, BookmarkEntity> observeBookmarkList();

    @NonNull
    Single<Integer> checkIfExistUrl(String Url);

    @NonNull
    Completable observeAddNewBookmark(@NonNull NewBookmarkRequest request);
}
