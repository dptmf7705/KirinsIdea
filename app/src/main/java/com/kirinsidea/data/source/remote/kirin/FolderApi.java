package com.kirinsidea.data.source.remote.kirin;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.kirin.response.NewFolderResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FolderApi {

    @POST(Constants.Retrofit.SUB_URL_NEW_FOLDER)
    Single<NewFolderResponse> observeAddNewFolder(@Body NewFolderRequest request);
}
