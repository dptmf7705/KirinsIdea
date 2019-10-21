package com.kirinsidea.data.source.remote.kirin;

import androidx.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kirinsidea.BuildConfig;
import com.kirinsidea.data.source.remote.kirin.api.BookmarkApi;
import com.kirinsidea.data.source.remote.kirin.api.FileApi;
import com.kirinsidea.data.source.remote.kirin.api.FolderApi;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.kirin.response.NewBookmarkResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class RetrofitClient {
    private static final String BASE_URL = "http://133.186.146.239:5000/";

    private static class LazyHolder {
        private static final RetrofitClient INSTANCE = new RetrofitClient();
    }

    @NonNull
    public static RetrofitClient getInstance() {
        return LazyHolder.INSTANCE;
    }

    @NonNull
    private final Retrofit retrofit;

    private RetrofitClient() {
        final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();

        setHeader(httpClientBuilder);
        setLogger(httpClientBuilder);
        setErrorHandler(httpClientBuilder);
        setTimeout(httpClientBuilder);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClientBuilder.build())
                .build();
    }

    /**
     * Header 설정
     */
    private void setHeader(OkHttpClient.Builder builder) {
        builder.addInterceptor(chain ->
                chain.proceed(chain.request().newBuilder()
//                        .header("UserToken", "")
                        .build()));
    }

    /**
     * Logger 설정
     */
    private void setLogger(OkHttpClient.Builder builder) {
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY));
        }
    }

    /**
     * ErrorHandler 설정
     */
    private void setErrorHandler(OkHttpClient.Builder builder) {
        // TODO custom error handling
    }

    /**
     * Timeout 설정
     */
    private void setTimeout(OkHttpClient.Builder builder) {
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);
    }

    @NonNull
    public Single<NewBookmarkResponse> observeAddNewBookmark(
            @NonNull final NewBookmarkRequest request) {

        return retrofit.create(BookmarkApi.class)
                .observeAddNewBookmark(request)
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    public Completable observeAddNewFolder(
            @NonNull final NewFolderRequest request) {

        return retrofit.create(FolderApi.class)
                .observeAddNewFolder(request)
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    public Single<ResponseBody> downloadFileByUrl(@Url String fileUrl) {
        return retrofit.create(FileApi.class)
                .downloadFileByUrl(fileUrl)
                .subscribeOn(Schedulers.io());
    }
}
