package com.kirinsidea.data.source.remote.kirin.request;

import androidx.annotation.NonNull;

public class NewBookmarkRequest {

    @NonNull
    private final String url;

    @NonNull
    private final int folderId;

    public NewBookmarkRequest(@NonNull final String url,
                              @NonNull final int folderId) {
        this.url = url;
        this.folderId = folderId;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    @NonNull
    public int getFolderId() {
        return folderId;
    }
}
