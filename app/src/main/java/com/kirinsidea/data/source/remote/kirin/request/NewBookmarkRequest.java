package com.kirinsidea.data.source.remote.kirin.request;

import androidx.annotation.NonNull;

public class NewBookmarkRequest {

    @NonNull
    private final String url;

    @NonNull
    private final String folderName;

    public NewBookmarkRequest(@NonNull final String url,
                              @NonNull final String folderName) {
        this.url = url;
        this.folderName = folderName;
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
