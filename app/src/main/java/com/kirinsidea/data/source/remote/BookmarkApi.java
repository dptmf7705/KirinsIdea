package com.kirinsidea.data.source.remote;

import com.kirinsidea.data.source.remote.request.WebClippingRequest;
import com.kirinsidea.data.source.remote.response.WebClippingResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookmarkApi {
    @POST("webclipp")
    Single<WebClippingResponse> getContentFromUrl(@Body WebClippingRequest webclippingRequest);

}
