package com.kirinsidea.data.source.remote.kirin;

import androidx.annotation.NonNull;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.data.source.remote.kirin.request.NewMemoRequest;
import com.kirinsidea.data.source.remote.kirin.response.NewBookmarkResponse;
import com.kirinsidea.utils.FileUtil;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class RetrofitClient {
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
        // TODO header 에 유저 아이디 추가
        final OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    final Request.Builder builder = chain.request().newBuilder();
//                        .header();
                    return chain.proceed(builder.build());
                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Retrofit.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
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
    public Completable observeAddNewMemo(
            @NonNull final NewMemoRequest request) {

        return retrofit.create(MemoApi.class)
                .observeAddNewMemo(request)
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    public Single<ResponseBody> downloadFileByUrl(@Url String fileUrl){
        return retrofit.create(FileApi.class)
                .downloadFileByUrl(fileUrl)
                .subscribeOn(Schedulers.io());
    }
}
