package com.kirinsidea.data.source.remote.kirin.error;

import androidx.annotation.NonNull;

public enum RetrofitResultCode {

    SUCCESS(200, "SUCCESS"),
    // 로그인
    LOGIN_ERROR_404(404, "LOGIN_ERROR_404"),
    LOGIN_ERROR_405(405, "이미 가입된 계정입니다.");

    private final int code;
    @NonNull
    private final String message;

    RetrofitResultCode(int code, @NonNull String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @NonNull
    public String getMessage() {
        return message;
    }

    public static RetrofitResultCode get(int code) {
        RetrofitResultCode[] values = RetrofitResultCode.values();
        for (RetrofitResultCode value : values) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return SUCCESS;
    }

}
