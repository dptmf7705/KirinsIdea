package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.Folder;
import com.kirinsidea.data.source.remote.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.response.NewFolderResponse;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface FolderRepository extends BaseRepository {
    @NonNull
    Single<Folder> observeFolderByName(final String folderName);

    @NonNull
    Single<Folder> observeFolderList();

    @NonNull
    Completable observeAddNewFolder(@NonNull final Folder folder);

    @NonNull
    Single<NewFolderResponse> observeAddNewFolder(@NonNull NewFolderRequest newFolderRequest);
}
