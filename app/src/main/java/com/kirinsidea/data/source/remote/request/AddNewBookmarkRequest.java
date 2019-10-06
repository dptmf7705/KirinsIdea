package com.kirinsidea.data.source.remote.request;

import androidx.annotation.NonNull;

public class AddNewBookmarkRequest {
    @NonNull
    private final String userId;

    @NonNull
    private final String url;

    @NonNull
    private final String folderName;

    public AddNewBookmarkRequest(@NonNull String userId, @NonNull String url, @NonNull String folderName) {
        this.userId = userId;
        this.url = url;
        this.folderName = folderName;
    }
    @NonNull
    public String getUserId(){return userId;}

    @NonNull
    public String getUrl() {
        return url;
    }

    @NonNull
    public String getFolderName(){return folderName;}
}
