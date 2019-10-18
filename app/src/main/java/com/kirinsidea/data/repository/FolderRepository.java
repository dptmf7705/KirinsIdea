package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.FolderEntity;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface FolderRepository extends BaseRepository {
    @NonNull
    Single<FolderEntity> observeFolderByName(final String folderName);

    @NonNull
    Single<List<FolderEntity>> observeFolderList();

    @NonNull
    Completable observeAddNewFolder(@NonNull NewFolderRequest request);
}
