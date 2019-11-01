package com.kirinsidea.data.source.remote.kirin.mock;

import com.kirinsidea.data.source.remote.kirin.api.FileApi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.reactivex.Single;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public class FileMockApi implements FileApi {
    @Streaming
    @GET
    public Single<ResponseBody> downloadFileByUrl(@Url String fileUrl){
        return Single.just(new ResponseBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public long contentLength() {
                return 0;
            }

            @NotNull
            @Override
            public BufferedSource source() {
                return null;
            }
        });
    }
}
