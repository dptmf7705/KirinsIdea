package com.kirinsidea.data.source.remote;

import androidx.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kirinsidea.data.source.remote.request.FolderRequest;
import com.kirinsidea.data.source.remote.request.WebClippingRequest;
import com.kirinsidea.data.source.remote.response.FolderResponse;
import com.kirinsidea.data.source.remote.response.WebClippingResponse;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://";

    @NonNull
    private final Retrofit retrofit;

    @NonNull
    public static RetrofitClient getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final RetrofitClient INSTANCE = new RetrofitClient();
    }


    public RetrofitClient() {

        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder.addInterceptor(chain -> {
            final Request.Builder builder = chain.request().newBuilder();
//                        .header();
            return chain.proceed(builder.build());
        });

        final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClientBuilder.build());

        retrofit = retrofitBuilder.build();

    }

    public Single<WebClippingResponse> getContentFromUrl(WebClippingRequest webClippingRequest) {
        return retrofit.create(BookmarkApi.class)
                .getContentFromUrl(webClippingRequest)
                .subscribeOn(Schedulers.io());
    }

    public Single<FolderResponse> getFolderStorageTime(FolderRequest folderRequest){
        return retrofit.create(FolderApi.class)
                .getFolderStorageTime(folderRequest)
                .subscribeOn(Schedulers.io());
    }
}
