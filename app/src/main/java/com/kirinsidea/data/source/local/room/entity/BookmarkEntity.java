package com.kirinsidea.data.source.local.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 북마크
 *
 * @member id               북마크 ID (PK)
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
    @PrimaryKey(autoGenerate = true)
    private final int id;
    @NonNull
    private final String originalWebUrl;
    @NonNull
    private final String simpleWebUrl;
    @NonNull
    private final String mainImageUrl;
    @NonNull
    private final String title;
    @NonNull
    private final String author;
    @NonNull
    private final String writeTime;
    @NonNull
    private final String path;
    @NonNull
    private final String storageTime;
    private final int folderId;

    public BookmarkEntity(final int id,
                          @NonNull final String originalWebUrl,
                          @NonNull final String simpleWebUrl,
                          @NonNull final String mainImageUrl,
                          @NonNull final String title,
                          @NonNull final String author,
                          @NonNull final String writeTime,
                          @NonNull final String path,
                          @NonNull final String storageTime,
                          final int folderId) {
        this.id = id;
        this.originalWebUrl = originalWebUrl;
        this.simpleWebUrl = simpleWebUrl;
        this.mainImageUrl = mainImageUrl;
        this.title = title;
        this.author = author;
        this.writeTime = writeTime;
        this.path = path;
        this.storageTime = storageTime;
        this.folderId = folderId;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getOriginalWebUrl() {
        return originalWebUrl;
    }

    @NonNull
    public String getSimpleWebUrl() {
        return simpleWebUrl;
    }

    @NonNull
    public String getMainImageUrl() {
        return mainImageUrl;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    @NonNull
    public String getWriteTime() {
        return writeTime;
    }

    @NonNull
    public String getPath() {
        return path;
    }

    @NonNull
    public String getStorageTime() {
        return storageTime;
    }

    public int getFolderId() {
        return folderId;
    }
}
