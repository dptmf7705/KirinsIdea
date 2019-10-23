package com.kirinsidea.data.source.remote.kirin.response;

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

    @SerializedName("storetime")
    @Expose
    @Nullable
    private final String storetime;

    @SerializedName("title")
    @Expose
    @Nullable
    private final String title;

    @SerializedName("originalweburl")
    @Expose
    @Nullable
    private final String originalweburl;

    @SerializedName("hosturl")
    @Expose
    @Nullable
    private final String hosturl;

    @SerializedName("folder")
    @Expose
    @Nullable
    private final String folder;

    public NewBookmarkResponse(@Nullable final String html,
                               @Nullable final String mainimage,
                               @Nullable final String author,
                               @Nullable final String writetime,
                               @Nullable final String storetime,
                               @Nullable final String title,
                               @Nullable final String originalweburl,
                               @Nullable final String hosturl,
                               @Nullable final String folder) {
        this.html = html;
        this.mainimage = mainimage;
        this.author = author;
        this.writetime = writetime;
        this.storetime = storetime;
        this.title = title;
        this.originalweburl = originalweburl;
        this.hosturl = hosturl;
        this.folder = folder;
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
    public String getStoretime() {
        return storetime;
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
    public String getHosturl() {
        return hosturl;
    }

    @Nullable
    public String getFolder() {
        return folder;
    }

    @Override
    public String toString() {
        return "NewBookmarkResponse{" +
                "html='" + html + '\'' +
                ", mainimage='" + mainimage + '\'' +
                ", author='" + author + '\'' +
                ", writetime='" + writetime + '\'' +
                ", storetime='" + storetime + '\'' +
                ", title='" + title + '\'' +
                ", originalweburl='" + originalweburl + '\'' +
                ", hosturl='" + hosturl + '\'' +
                ", folder='" + folder + '\'' +
                '}';
    }
}
