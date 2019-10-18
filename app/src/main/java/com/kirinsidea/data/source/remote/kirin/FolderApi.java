package com.kirinsidea.data.source.remote.kirin;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;

import io.reactivex.Completable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FolderApi {

    @POST(Constants.Retrofit.SUB_URL_NEW_FOLDER)
    Completable observeAddNewFolder(@Body NewFolderRequest request);
    }
