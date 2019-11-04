package com.kirinsidea.data.source.remote.kirin.mock;

import com.kirinsidea.data.source.remote.kirin.api.FolderApi;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.kirin.response.NewFolderResponse;
import com.kirinsidea.utils.DateUtil;

import java.util.Random;

import io.reactivex.Single;

public class FolderMockApi implements FolderApi {

    @Override
    public Single<NewFolderResponse> addNewFolder(NewFolderRequest request) {
        return Single.just(new NewFolderResponse(request.getFolderName(), new Random().nextInt(), DateUtil.getCurrentDateTime()));
    }
}
