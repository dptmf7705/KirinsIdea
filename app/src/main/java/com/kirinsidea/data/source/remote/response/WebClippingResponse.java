package com.kirinsidea.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WebClippingResponse {
    @SerializedName("html")
    @Expose
    private final String html;

    @SerializedName("mainimage")
    @Expose
    private final String mainimage;

    @SerializedName("author")
    @Expose
    private final String author;

    @SerializedName("writetime")
    @Expose
    private final String writetime;

    @SerializedName("storagetime")
    @Expose
    private final String storagetime;

    @SerializedName("title")
    @Expose
    private final String title;

    @SerializedName("originalweburl")
    @Expose
    private final String originalweburl;

    @SerializedName("simpleweburl")
    @Expose
    private final String simpleweburl;

    public WebClippingResponse(String html, String mainimage, String author, String writetime, String storagetime, String title, String originalweburl, String simpleweburl) {
        this.html = html;
        this.mainimage = mainimage;
        this.author = author;
        this.writetime = writetime;
        this.storagetime = storagetime;
        this.title = title;
        this.originalweburl = originalweburl;
        this.simpleweburl = simpleweburl;
    }

    public String getHtml() {
        return html;
    }

    public String getMainimage() {
        return mainimage;
    }

    public String getAuthor() {
        return author;
    }

    public String getWritetime() {
        return writetime;
    }

    public String getStoragetime() {
        return storagetime;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalweburl() {
        return originalweburl;
    }

    public String getSimpleweburl() {
        return simpleweburl;
    }

    @Override
    public String toString() {
        return "WebClippingResponse{" +
                "html=" + html + '\'' +
                ", mainimage=" + mainimage + '\'' +
                ", author=" + author + '\'' +
                ", writetime=" + writetime + '\'' +
                ", storagetime=" + storagetime + '\'' +
                ", title=" + title + '\'' +
                ", originalweburl=" + originalweburl + '\'' +
                ", simpleweburl=" + simpleweburl + '\'' +
                '}';
    }
}
