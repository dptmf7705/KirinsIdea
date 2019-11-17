package com.kirinsidea.data.source.local.room.error;

import androidx.annotation.NonNull;

public enum RoomResult {
    NULL("NULL"),
    ALREADY_EXIST("ALREADY_EXIST"),
    BOOKMARK_ALREADY_EXIST("이미 북마크가 존재합니다."),
    FOLDER_ALREADY_EXIST("같은 이름의 폴더가 존재합니다.");

    @NonNull
    private final String message;

    RoomResult(@NonNull String message) {
        this.message = message;
    }

    @NonNull
    public String getMessage() {
        return message;
    }
}
