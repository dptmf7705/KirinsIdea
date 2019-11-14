package com.kirinsidea.data.source.remote.kirin.response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitResultCode;

public class SingleResponse<T> {
    @SerializedName("result_code")
    @Expose
    @NonNull
    private final RetrofitResultCode retrofitResultCode;
    @SerializedName("result")
    @Expose
    @NonNull
    private final T result;

    public SingleResponse(@NonNull RetrofitResultCode retrofitResultCode, @NonNull T result) {
        this.retrofitResultCode = retrofitResultCode;
        this.result = result;
    }

    @NonNull
    public RetrofitResultCode getRetrofitResultCode() {
        return retrofitResultCode;
    }

    @NonNull
    public T getResult() {
        return result;
    }
}
