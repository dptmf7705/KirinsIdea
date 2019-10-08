package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.local.room.entity.Folder;
import com.kirinsidea.data.source.remote.RetrofitClient;
import com.kirinsidea.data.source.remote.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.response.NewFolderResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class FolderRepositoryImpl implements FolderRepository {

    private volatile static FolderRepository INSTANCE = null;

    public static FolderRepository getInstance(@NonNull final RetrofitClient retrofit,
                                               @NonNull final FolderDao folderDao) {
        if (INSTANCE == null) {
            synchronized (FolderRepositoryImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FolderRepositoryImpl(retrofit, folderDao);
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    private RetrofitClient retrofit;
    @NonNull
    private final FolderDao folderDao;

    public FolderRepositoryImpl(@NonNull final RetrofitClient retrofit,
                                @NonNull final FolderDao folderDao) {
        this.retrofit = retrofit;
        this.folderDao = folderDao;
    }

    @NonNull
    @Override
    public Single<Folder> observeFolderByName(String folderName) {
        return folderDao.selectByName(folderName).subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<Folder> observeFolderList() {
        return folderDao.selectAll();
    }

    @NonNull
    @Override
    public Completable observeAddNewFolder(@NonNull Folder folder) {
        return folderDao.insert(folder).subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<NewFolderResponse> observeAddNewFolder(@NonNull NewFolderRequest newFolderRequest) {
        return retrofit.observeAddNewFolder(newFolderRequest).subscribeOn(Schedulers.io());
    }
}
