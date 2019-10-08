package com.kirinsidea.data.source.remote;

import com.kirinsidea.common.Constant;
import com.kirinsidea.data.source.remote.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.response.NewFolderResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FolderApi {

    @POST(Constant.Retrofit.SUB_URL_FOLDER)
    Single<NewFolderResponse> observeAddNewFolder(@Body NewFolderRequest newFolderRequest);
}
