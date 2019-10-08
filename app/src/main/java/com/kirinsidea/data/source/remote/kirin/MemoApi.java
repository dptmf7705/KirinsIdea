package com.kirinsidea.data.source.remote.kirin;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.remote.kirin.request.NewMemoRequest;

import io.reactivex.Completable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MemoApi {

    @POST(Constants.Retrofit.SUB_URL_NEW_MEMO)
    Completable observeAddNewMemo(@Body NewMemoRequest request);
}
