package com.kirinsidea.data.source.remote.kirin.api;

import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.kirin.request.ChangeFolderRequest;
import com.kirinsidea.data.source.remote.kirin.response.NewFolderResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FolderApi {

    @POST("folder")
    Single<NewFolderResponse> addNewFolder(@Body NewFolderRequest request);

    @POST("folderUpdqte")
    Completable changeFolderName(@Body ChangeFolderRequest request);

    @POST("folderDelete")
    Completable deleteFolder(@Body ChangeFolderRequest request);
}
