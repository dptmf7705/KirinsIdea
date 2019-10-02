package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.kirinsidea.data.source.local.room.entity.Bookmark;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BookmarkRepository {

    @NonNull
    Single<Bookmark> observeBookmarkById(final int id);

    @NonNull
    DataSource.Factory<Integer, Bookmark> observeBookmarkList();

    @NonNull
    Completable observeAddNewBookmark(@NonNull final Bookmark bookmark);
}
