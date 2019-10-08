package com.kirinsidea.data.source.remote;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.remote.request.NewBookmarkRequest;
import com.kirinsidea.data.source.remote.response.NewBookmarkResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookmarkApi {

    @POST(Constants.Retrofit.SUB_URL_NEW_BOOKMARK)
    Single<NewBookmarkResponse> observeAddNewBookmark(@Body NewBookmarkRequest request);
}
