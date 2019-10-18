package com.kirinsidea.data.source.remote.mapper;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.data.source.remote.kirin.response.NewBookmarkResponse;

public class BookmarkResponseMapper {
    @NonNull
    public static Bookmark toBookmark(@NonNull final NewBookmarkResponse response, @NonNull final String path){

        return new Bookmark(0,response.getOriginalweburl(),response.getSimpleweburl(),response.getMainimage()
        ,response.getTitle(), response.getAuthor(), response.getWritetime(), path,response.getStoragetime(), response.getFolderName());
    }
}
