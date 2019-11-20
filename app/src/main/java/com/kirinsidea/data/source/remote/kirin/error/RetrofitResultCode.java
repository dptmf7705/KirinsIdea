package com.kirinsidea.data.source.remote.kirin.error;

import androidx.annotation.NonNull;

public enum RetrofitResultCode {

    SUCCESS(200, "SUCCESS"),
    // 로그인
    LOGIN_ERROR_404(404, "LOGIN_ERROR_404"),
    LOGIN_ERROR_405(405, "이미 가입된 계정입니다."),
    // 북마크 추가
    BOOKMARK_ERROR_406(406,"예기치 못한 에러 발생, 다시 시도해 주세요"),
    BOOKMARK_ERROR_407(407,"파일을 다운받지 못하였습니다."),
    // 폴더 추가
    FOLDER_ERROR_501(501,"예기치 못한 에러 발생, 다시 시도해 주세요");

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
