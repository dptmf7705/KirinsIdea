package com.kirinsidea.data.source.remote.kirin.response;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewFolderResponse {
    @SerializedName("folderName")
    @Expose
    @Nullable
    private final String folderName;

    @SerializedName("storagetime")
    @Expose
    @Nullable
    private final String storagetime;

    public NewFolderResponse(@Nullable final String folderName,
                             @Nullable final String storagetime) {
        this.folderName = folderName;
        this.storagetime = storagetime;
    }

    @Nullable
    public String getFolderName() {
        return folderName;
    }

    @Nullable
    public String getStoragetime() {
        return storagetime;
    }

    @Override
    public String toString() {
        return "NewFolderResponse{" +
                "folderName='" + folderName + '\'' +
                ", storagetime='" + storagetime + '\'' +
                '}';
    }
}
