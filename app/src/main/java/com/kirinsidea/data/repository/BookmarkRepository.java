package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.data.source.local.room.entity.Folder;
import com.kirinsidea.data.source.remote.request.WebClippingRequest;
import com.kirinsidea.data.source.remote.response.WebClippingResponse;

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

    @NonNull
    Single<WebClippingResponse> uploadWeb(@NonNull WebClippingRequest webClippingRequest);
}
