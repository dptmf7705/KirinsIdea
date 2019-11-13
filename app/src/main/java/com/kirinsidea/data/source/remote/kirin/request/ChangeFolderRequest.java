package com.kirinsidea.data.source.remote.kirin.request;

import androidx.annotation.NonNull;

public class ChangeFolderRequest {

    @NonNull
    private final int folderId;
    @NonNull
    private final String folderName;

    public ChangeFolderRequest(int folderId, @NonNull String folderName) {
        this.folderId = folderId;
        this.folderName = folderName;
    }
    public int getFolderId() {
        return folderId;
    }

    @NonNull
    public String getFolderName() {
        return folderName;
    }
}
