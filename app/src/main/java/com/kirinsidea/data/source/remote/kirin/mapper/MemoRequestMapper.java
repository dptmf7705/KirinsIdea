package com.kirinsidea.data.source.remote.kirin.mapper;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.MemoEntity;
import com.kirinsidea.data.source.remote.kirin.request.NewMemoRequest;

public class MemoRequestMapper {

    @NonNull
    public static MemoEntity toMemo(@NonNull final NewMemoRequest request) {
        return new MemoEntity(
                request.getStartIndex(),
                request.getEndIndex(),
                request.getText(),
                request.getMemo(),
                request.getBookmarkId());
    }
}
