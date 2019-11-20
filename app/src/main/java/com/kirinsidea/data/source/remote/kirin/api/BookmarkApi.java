package com.kirinsidea.data.source.remote.kirin.api;

import com.kirinsidea.data.source.entity.BookmarkEntity;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.data.source.remote.kirin.response.SingleResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookmarkApi {

    @POST("webclipp")
    Single<SingleResponse<BookmarkEntity>> addNewBookmark(@Body NewBookmarkRequest request);
}
