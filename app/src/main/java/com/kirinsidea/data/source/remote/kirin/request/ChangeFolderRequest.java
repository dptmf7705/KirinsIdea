package com.kirinsidea.data.source.remote.kirin.request;

import androidx.annotation.NonNull;

public class ChangeFolderRequest {

    @NonNull
    private final String folderId;
    @NonNull
    private final String folderName;

    public ChangeFolderRequest(String folderId, @NonNull String folderName) {
        this.folderId = folderId;
        this.folderName = folderName;
    }
    public String getFolderId() {
        return folderId;
    }

    @NonNull
    public String getFolderName() {
        return folderName;
    }
}
