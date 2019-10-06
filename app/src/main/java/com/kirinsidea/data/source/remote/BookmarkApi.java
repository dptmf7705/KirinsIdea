package com.kirinsidea.data.source.remote;

import com.kirinsidea.data.source.remote.request.AddNewBookmarkRequest;
import com.kirinsidea.data.source.remote.response.AddNewBookmarkResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookmarkApi {
    @POST("webclipp")
    Single<AddNewBookmarkResponse> getContentFromUrl(@Body AddNewBookmarkRequest webclippingRequest);

}
