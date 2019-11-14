package com.kirinsidea.data.repository.folder;

import androidx.annotation.NonNull;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.entity.FolderEntity;
import com.kirinsidea.data.source.remote.kirin.api.FolderApi;
import com.kirinsidea.data.source.remote.kirin.mock.FolderMockApi;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.kirin.request.ChangeFolderRequest;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class FolderRepositoryImpl implements FolderRepository {
    private static class LazyHolder {
        private static final FolderRepository INSTANCE = new FolderRepositoryImpl();
    }

    @NonNull
    public static FolderRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private FolderRepositoryImpl() {
    }

    private FolderDao folderLocalDataSource;
    private FolderApi folderRemoteDataSource;

    @NonNull
    @Override
    public BaseRepository init(@NonNull Object... dataSources) {
        this.folderLocalDataSource = (FolderDao) dataSources[0];
//        this.folderRemoteDataSource = (FolderApi) dataSources[1];
        this.folderRemoteDataSource = new FolderMockApi();
        return this;
    }

    @NonNull
    @Override
    public Single<FolderEntity> observeFolderById(int folderId) {
        return folderLocalDataSource.selectById(folderId).subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<List<FolderEntity>> observeFolderList() {
        return folderLocalDataSource.selectFolderList().subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<Integer> observeAddNewFolder(@NonNull NewFolderRequest request) {
        return folderRemoteDataSource.addNewFolder(request)
                .map(response -> new FolderEntity.Builder(response).setFavorite(false).build())
                .flatMap(entity -> folderLocalDataSource.insert(entity).toSingleDefault(entity.getId()))
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Completable observeChangeFavorite(@NonNull FolderEntity entity) {
        return folderLocalDataSource.update(entity).subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<Integer> observeChangeFolderName(@NonNull ChangeFolderRequest request
            , @NonNull FolderEntity entity) {
        return folderRemoteDataSource.changeFolderName(request)
                .andThen(folderLocalDataSource.update(entity).toSingleDefault(entity.getId()))
                .subscribeOn(Schedulers.io());
    }
}
