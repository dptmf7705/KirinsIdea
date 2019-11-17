package com.kirinsidea.data.source.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kirinsidea.ui.bookmark.Bookmark;
import com.kirinsidea.ui.bookmarklist.BookmarkItem;

/**
 * 북마크
 *
 * @member id               북마크 ID (PK)
 * @member html             웹클리핑
 * @member originalWebUrl   웹 페이지 URL
 * @member simpleWebUrl     웹 페이지 URL 중 플랫폼 이름까지
 * @member mainImageUrl     메인 이미지 URL
 * @member title            포스팅 제목
 * @member author           포스팅 저자
 * @member writeTime        포스팅 시간
 * @member path             로컬 저장 경로
 * @member storageTime      서버 저장 시간
 * @member folderId         저장 폴더 ID
 */
@Entity(tableName = "bookmark")
public class BookmarkEntity {
    @PrimaryKey
    @NonNull
    private String id;

    @SerializedName("html")
    @Expose
    @Nullable
    private String html;

    @SerializedName("originalweburl")
    @Expose
    @Nullable
    private String originalWebUrl;

    @SerializedName("hosturl")
    @Expose
    @Nullable
    private String simpleWebUrl;

    @SerializedName("mainimage")
    @Expose
    @Nullable
    private String mainImageUrl;

    @SerializedName("title")
    @Expose
    @Nullable
    private String title;

    @SerializedName("author")
    @Expose
    @Nullable
    private String author;

    @SerializedName("writetime")
    @Expose
    @Nullable
    private String writeTime;

    private String path;

    @SerializedName("storetime")
    @Expose
    @Nullable
    private String storageTime;

    @SerializedName("folderId")
    @Expose
    @Nullable
    private String folderId;

    public BookmarkEntity() {
    }

    public BookmarkEntity(Builder builder) {
        this.id = builder.id;
        this.originalWebUrl = builder.originalWebUrl;
        this.simpleWebUrl = builder.simpleWebUrl;
        this.mainImageUrl = builder.mainImageUrl;
        this.title = builder.title;
        this.author = builder.author;
        this.writeTime = builder.writeTime;
        this.path = builder.path;
        this.storageTime = builder.storageTime;
        this.folderId = builder.folderId;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public String getHtml() {
        return html;
    }

    @Nullable
    public String getOriginalWebUrl() {
        return originalWebUrl;
    }

    @Nullable
    public String getSimpleWebUrl() {
        return simpleWebUrl;
    }

    @Nullable
    public String getMainImageUrl() {
        return mainImageUrl;
    }

    @Nullable
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }

    @Nullable
    public String getWriteTime() {
        return writeTime;
    }

    public String getPath() {
        return path;
    }

    @Nullable
    public String getStorageTime() {
        return storageTime;
    }

    @Nullable
    public String getFolderId() {
        return folderId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHtml(@Nullable String html) {
        this.html = html;
    }

    public void setOriginalWebUrl(@Nullable String originalWebUrl) {
        this.originalWebUrl = originalWebUrl;
    }

    public void setSimpleWebUrl(@Nullable String simpleWebUrl) {
        this.simpleWebUrl = simpleWebUrl;
    }

    public void setMainImageUrl(@Nullable String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    public void setAuthor(@Nullable String author) {
        this.author = author;
    }

    public void setWriteTime(@Nullable String writeTime) {
        this.writeTime = writeTime;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setStorageTime(@Nullable String storageTime) {
        this.storageTime = storageTime;
    }

    public void setFolderId(@Nullable String folderId) {
        this.folderId = folderId;
    }

    public static class Builder {
        private final String id;
        private String originalWebUrl;
        private final String simpleWebUrl;
        private final String title;
        private String author;
        private String writeTime;
        private String mainImageUrl;
        private String storageTime;
        private String folderId;
        private String path = "";

        public Builder(@NonNull final Bookmark model) {
            this.id = model.getId();
            this.originalWebUrl = model.getOriginalWebUrl();
            this.simpleWebUrl = model.getSimpleWebUrl();
            this.title = model.getTitle();
            this.author = model.getAuthor();
            this.writeTime = model.getWriteTime();
        }

        public Builder(@NonNull final BookmarkItem model) {
            this.id = model.getId();
            this.simpleWebUrl = model.getSimpleWebUrl();
            this.mainImageUrl = model.getMainImageUrl();
            this.title = model.getTitle();
            this.storageTime = model.getStorageTime();
            this.folderId = model.getFolderId();
        }

        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public BookmarkEntity build() {
            return new BookmarkEntity(this);
        }
    }
}