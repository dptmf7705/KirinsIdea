package com.kirinsidea.data.source.remote.kirin.api;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.data.source.remote.kirin.response.NewBookmarkResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookmarkApi {

    @POST(Constants.Retrofit.SUB_URL_NEW_BOOKMARK)
    Single<NewBookmarkResponse> observeAddNewBookmark(@Body NewBookmarkRequest request);
}
