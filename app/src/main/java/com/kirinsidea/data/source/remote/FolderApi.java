package com.kirinsidea.data.source.remote;

import com.kirinsidea.data.source.remote.request.FolderRequest;
import com.kirinsidea.data.source.remote.response.FolderResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FolderApi {
    @POST("folder")
    Single<FolderResponse> getFolderStorageTime(@Body FolderRequest folderRequest);
}
