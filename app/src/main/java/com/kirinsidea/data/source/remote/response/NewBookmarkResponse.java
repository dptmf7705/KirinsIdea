package com.kirinsidea.data.source.remote.response;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewBookmarkResponse {
    @SerializedName("html")
    @Expose
    @Nullable
    private final String html;

    @SerializedName("mainimage")
    @Expose
    @Nullable
    private final String mainimage;

    @SerializedName("author")
    @Expose
    @Nullable
    private final String author;

    @SerializedName("writetime")
    @Expose
    @Nullable
    private final String writetime;

    @SerializedName("storagetime")
    @Expose
    @Nullable
    private final String storagetime;

    @SerializedName("title")
    @Expose
    @Nullable
    private final String title;

    @SerializedName("originalweburl")
    @Expose
    @Nullable
    private final String originalweburl;

    @SerializedName("simpleweburl")
    @Expose
    @Nullable
    private final String simpleweburl;

    @SerializedName("folderName")
    @Expose
    @Nullable
    private final String folderName;

    public NewBookmarkResponse(@Nullable final String html,
                               @Nullable final String mainimage,
                               @Nullable final String author,
                               @Nullable final String writetime,
                               @Nullable final String storagetime,
                               @Nullable final String title,
                               @Nullable final String originalweburl,
                               @Nullable final String simpleweburl,
                               @Nullable final String folderName) {
        this.html = html;
        this.mainimage = mainimage;
        this.author = author;
        this.writetime = writetime;
        this.storagetime = storagetime;
        this.title = title;
        this.originalweburl = originalweburl;
        this.simpleweburl = simpleweburl;
        this.folderName = folderName;
    }

    @Nullable
    public String getHtml() {
        return html;
    }

    @Nullable
    public String getMainimage() {
        return mainimage;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }

    @Nullable
    public String getWritetime() {
        return writetime;
    }

    @Nullable
    public String getStoragetime() {
        return storagetime;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getOriginalweburl() {
        return originalweburl;
    }

    @Nullable
    public String getSimpleweburl() {
        return simpleweburl;
    }

    @Nullable
    public String getFolderName() {
        return folderName;
    }

    @Override
    public String toString() {
        return "NewBookmarkResponse{" +
                "html='" + html + '\'' +
                ", mainimage='" + mainimage + '\'' +
                ", author='" + author + '\'' +
                ", writetime='" + writetime + '\'' +
                ", storagetime='" + storagetime + '\'' +
                ", title='" + title + '\'' +
                ", originalweburl='" + originalweburl + '\'' +
                ", simpleweburl='" + simpleweburl + '\'' +
                ", folderName='" + folderName + '\'' +
                '}';
    }
}
