package com.kirinsidea.mapper;

import androidx.annotation.NonNull;

import com.kirinsidea.ui.bookmarklist.BookmarkItem;
import com.kirinsidea.data.source.local.room.entity.BookmarkEntity;

public class BookmarkItemMapper {

    @NonNull
    public static BookmarkItem entityToModel(@NonNull final BookmarkEntity entity) {
        return new BookmarkItem(
                entity.getId(),
                entity.getSimpleWebUrl(),
                entity.getMainImageUrl(),
                entity.getTitle(),
                entity.getStorageTime());
    }
}
