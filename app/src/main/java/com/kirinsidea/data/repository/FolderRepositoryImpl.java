package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.local.room.entity.Folder;
import com.kirinsidea.data.source.remote.RetrofitClient;
import com.kirinsidea.data.source.remote.request.FolderRequest;
import com.kirinsidea.data.source.remote.response.FolderResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class FolderRepositoryImpl extends BaseRepository implements FolderRepository{

    private volatile static FolderRepository INSTANCE = null;

    public static FolderRepository getInstance(@NonNull final RetrofitClient client, @NonNull final FolderDao folderDao) {
        if (INSTANCE == null) {
            synchronized (FolderRepositoryImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FolderRepositoryImpl(client, folderDao);
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    private final FolderDao folderDao;
    @NonNull
    private RetrofitClient client;

    public FolderRepositoryImpl(@NonNull final RetrofitClient client, @NonNull FolderDao folderDao) {
        this.folderDao = folderDao;
        this.client = client;
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
    public  Single<FolderResponse> uploadFolderName(@NonNull FolderRequest folderRequest){
        return client.getFolderStorageTime(folderRequest).subscribeOn(Schedulers.io());
    }
}
