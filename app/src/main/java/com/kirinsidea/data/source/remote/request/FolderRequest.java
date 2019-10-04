package com.kirinsidea.data.source.remote.request;

import androidx.annotation.NonNull;

public class FolderRequest {
    @NonNull
    private final String folderName;

    public FolderRequest(@NonNull String folderName) {
        this.folderName = folderName;
    }
    @NonNull
    public String getFolderName(){return folderName;}
}
