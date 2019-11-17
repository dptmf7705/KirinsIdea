package com.kirinsidea.data.repository.folder;

import androidx.annotation.NonNull;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.source.entity.FolderEntity;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.kirin.request.ChangeFolderRequest;
import com.kirinsidea.ui.folderlist.Folder;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface FolderRepository extends BaseRepository {
    @NonNull
    Maybe<Folder> observeFolderById(final String folderId);

    @NonNull
    Single<List<Folder>> observeFolderList();

    @NonNull
    Single<String> observeAddNewFolder(@NonNull NewFolderRequest request);

    @NonNull
    Completable observeChangeFavorite(@NonNull Folder folder);

    @NonNull
    Single<String> observeChangeFolderName(@NonNull ChangeFolderRequest request, @NonNull Folder folder);

    Maybe<String> observeBookmarkByFavorite();
}
