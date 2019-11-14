package com.kirinsidea.data.repository.folder;

import androidx.annotation.NonNull;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.source.entity.FolderEntity;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.kirin.request.ChangeFolderRequest;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface FolderRepository extends BaseRepository {
    @NonNull
    Single<FolderEntity> observeFolderById(final int folderId);

    @NonNull
    Single<List<FolderEntity>> observeFolderList();

    @NonNull
    Single<Integer> observeAddNewFolder(@NonNull NewFolderRequest request);

    @NonNull
    Completable observeChangeFavorite(@NonNull FolderEntity entity);

    @NonNull
    Single<Integer> observeChangeFolderName(@NonNull ChangeFolderRequest request, @NonNull FolderEntity entity);
}
