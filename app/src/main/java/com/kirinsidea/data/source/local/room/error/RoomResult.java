package com.kirinsidea.data.source.local.room.error;

import androidx.annotation.NonNull;

public enum RoomResult {
    NULL("NULL"),
    ALREADY_EXIST("ALREADY_EXIST");

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
