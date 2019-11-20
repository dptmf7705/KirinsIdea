package com.kirinsidea.data.source.remote.kirin.request;

import androidx.annotation.NonNull;

public class NewBookmarkRequest {

    @NonNull
    private final String url;

    @NonNull
    private final String folderId;

    public NewBookmarkRequest(@NonNull final String url,
                              @NonNull final String folderId) {
        this.url = url;
        this.folderId = folderId;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    @NonNull
    public String getFolderId() {
        return folderId;
    }
}
