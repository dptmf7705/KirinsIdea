package com.kirinsidea.data.source.remote.request;

import androidx.annotation.NonNull;

public class NewFolderRequest {
    @NonNull
    private final String folderName;

    public NewFolderRequest(@NonNull String folderName) {
        this.folderName = folderName;
    }
    @NonNull
    public String getFolderName(){return folderName;}
}
