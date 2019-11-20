package com.kirinsidea.data.source.remote.kirin.mock;

import com.kirinsidea.data.source.entity.FolderEntity;
import com.kirinsidea.data.source.remote.kirin.api.FolderApi;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitResultCode;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.kirin.request.ChangeFolderRequest;
import com.kirinsidea.data.source.remote.kirin.response.SingleResponse;
import com.kirinsidea.ui.folderlist.Folder;

import java.util.Random;

import io.reactivex.Completable;
import io.reactivex.Single;

public class FolderMockApi implements FolderApi {

    @Override
    public Single<SingleResponse<FolderEntity>> addNewFolder(NewFolderRequest request) {
        FolderEntity folder = new FolderEntity.Builder(new Folder(String.valueOf(new Random().nextInt()),request.getFolderName(),
                false,false)).build();
        SingleResponse<FolderEntity> response = new SingleResponse<>(RetrofitResultCode.SUCCESS, folder);

        return Single.just(response);
    }

    @Override
    public Completable changeFolderName(ChangeFolderRequest request) {
        return null;
    }

    @Override
    public Completable deleteFolder(ChangeFolderRequest request) {
        return null;
    }
}
