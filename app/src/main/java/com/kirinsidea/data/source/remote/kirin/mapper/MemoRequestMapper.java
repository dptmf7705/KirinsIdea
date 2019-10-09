package com.kirinsidea.data.source.remote.kirin.mapper;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.Memo;
import com.kirinsidea.data.source.remote.kirin.request.NewMemoRequest;

public class MemoRequestMapper {

    @NonNull
    public static Memo toMemo(@NonNull final NewMemoRequest request) {
        return new Memo(0,
                request.getStartIndex(),
                request.getEndIndex(),
                request.getText(),
                request.getMemo(),
                request.getBookmarkId());
    }
}
