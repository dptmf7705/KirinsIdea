package com.kirinsidea.data.source.remote.kirin.request;

import androidx.annotation.NonNull;

public class NewBookmarkRequest {
    @NonNull
    private final String userId;

    @NonNull
    private final String url;

    @NonNull
    private final String folderName;

    public NewBookmarkRequest(@NonNull final String userId,
                              @NonNull final String url,
                              @NonNull final String folderName) {
        this.userId = userId;
        this.url = url;
        this.folderName = folderName;
    }

    @NonNull
    public String getUserId() {
        return userId;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    @NonNull
    public String getFolderName() {
        return folderName;
    }
}
