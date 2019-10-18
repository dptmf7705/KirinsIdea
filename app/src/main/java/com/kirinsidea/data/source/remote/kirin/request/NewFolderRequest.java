package com.kirinsidea.data.source.remote.kirin.request;

import androidx.annotation.NonNull;

public class NewFolderRequest {
    @NonNull
    private final String folderName;

    @NonNull
    private final String storageTime;

    public NewFolderRequest(@NonNull final String folderName, @NonNull String storageTime) {
        this.folderName = folderName;
        this.storageTime = storageTime;
    }

    @NonNull
    public String getFolderName() {
        return folderName;
    }

    @NonNull
    public  String getStorageTime(){
        return storageTime;
    }
}
