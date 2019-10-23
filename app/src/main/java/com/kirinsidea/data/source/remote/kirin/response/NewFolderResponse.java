package com.kirinsidea.data.source.remote.kirin.response;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewFolderResponse {
    @SerializedName("folder")
    @Expose
    @Nullable
    private final String name;

    @SerializedName("folderid")
    @Expose
    @Nullable
    private final int id;

    @SerializedName("storetime")
    @Expose
    @Nullable
    private final String storeTime;


    public NewFolderResponse(@Nullable String name,
                             int id,
                             @Nullable String storeTime) {
        this.name = name;
        this.id = id;
        this.storeTime = storeTime;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Nullable
    public String getStoreTime() {
        return storeTime;
    }

    @Override
    public String toString() {
        return "NewFolderResponse{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", storeTime='" + storeTime + '\'' +
                '}';
    }
}
