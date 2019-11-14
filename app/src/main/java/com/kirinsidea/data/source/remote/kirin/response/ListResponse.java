package com.kirinsidea.data.source.remote.kirin.response;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitResultCode;

import java.util.List;

public class ListResponse<T> {
    @SerializedName("result_code")
    @Expose
    @NonNull
    private final RetrofitResultCode retrofitResultCode;
    @SerializedName("result")
    @Expose
    @NonNull
    private final List<T> result;

    public ListResponse(@NonNull RetrofitResultCode retrofitResultCode, @NonNull List<T> result) {
        this.retrofitResultCode = retrofitResultCode;
        this.result = result;
    }

    @NonNull
    public RetrofitResultCode getRetrofitResultCode() {
        return retrofitResultCode;
    }

    @NonNull
    public List<T> getResult() {
        return result;
    }
}
