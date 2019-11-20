package com.kirinsidea.data.source.remote.kirin.error;

import androidx.annotation.NonNull;

public class FileDownloadException extends Throwable{
    @NonNull
    private final RetrofitResultCode retrofitResultCode;

    public FileDownloadException(@NonNull RetrofitResultCode retrofitResultCode) {
        super(retrofitResultCode.getMessage());
        this.retrofitResultCode = retrofitResultCode;
    }

    @NonNull
    public RetrofitResultCode getRetrofitResultCode() {
        return retrofitResultCode;
    }
}
