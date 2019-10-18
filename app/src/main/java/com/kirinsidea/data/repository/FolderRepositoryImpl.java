package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.local.room.entity.FolderEntity;
import com.kirinsidea.data.source.remote.kirin.RetrofitClient;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;

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

    private RetrofitClient retrofit;
    private FolderDao folderDao;

    @NonNull
    @Override
    public BaseRepository init(@NonNull Object... dataSources) {
        this.retrofit = (RetrofitClient) dataSources[0];
        this.folderDao = (FolderDao) dataSources[1];
        return this;
    }

    @NonNull
    @Override
    public Single<FolderEntity> observeFolderByName(String folderName) {
        return folderDao.selectByName(folderName).subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<List<FolderEntity>> observeFolderList() {
        return folderDao.selectAll();
    }

    @NonNull
    @Override
    public Completable observeAddNewFolder(@NonNull NewFolderRequest request) {

        return retrofit.observeAddNewFolder(request)
                .mergeWith(folderDao.insert(new FolderEntity.Builder().fromRequest(request).build()))
                .subscribeOn(Schedulers.io());
    }
}
