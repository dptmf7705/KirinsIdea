package com.kirinsidea.data.source.remote.kirin;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface FileApi {
    @Streaming
    @GET
    Single<ResponseBody> downloadFileByUrl(@Url String fileUrl);
}
