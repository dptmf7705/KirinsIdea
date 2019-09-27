package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.Bookmark;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface BookmarkRepository {

    @NonNull
    Single<List<Bookmark>> observeBookmarkList();

    @NonNull
    Completable observeAddNewBookmark(@NonNull final Bookmark bookmark);
}
