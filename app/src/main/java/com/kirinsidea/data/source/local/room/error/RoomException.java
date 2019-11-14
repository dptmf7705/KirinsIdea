package com.kirinsidea.data.source.local.room.error;

import androidx.annotation.NonNull;

public class RoomException extends Throwable {
    @NonNull
    private final RoomResult roomResult;

    public RoomException(@NonNull RoomResult roomResult) {
        super(roomResult.getMessage());
        this.roomResult = roomResult;
    }

    @NonNull
    public RoomResult getRoomResult() {
        return roomResult;
    }
}
