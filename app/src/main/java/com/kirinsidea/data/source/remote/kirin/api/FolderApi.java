package com.kirinsidea.data.source.remote.kirin.api;

import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;

import io.reactivex.Completable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FolderApi {

    @POST("folder")
    Completable addNewFolder(@Body NewFolderRequest request);
}
