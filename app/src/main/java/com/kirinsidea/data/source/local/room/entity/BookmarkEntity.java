package com.kirinsidea.data.source.local.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kirinsidea.data.source.remote.kirin.response.NewBookmarkResponse;

import java.util.Objects;

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
 * @member folderName       저장 폴더 이름
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
    @NonNull
    private final String folderName;

    public BookmarkEntity(final int id,
                          @NonNull final String originalWebUrl,
                          @NonNull final String simpleWebUrl,
                          @NonNull final String mainImageUrl,
                          @NonNull final String title,
                          @NonNull final String author,
                          @NonNull final String writeTime,
                          @NonNull final String path,
                          @NonNull final String storageTime,
                          @NonNull final String folderName) {
        this.id = id;
        this.originalWebUrl = originalWebUrl;
        this.simpleWebUrl = simpleWebUrl;
        this.mainImageUrl = mainImageUrl;
        this.title = title;
        this.author = author;
        this.writeTime = writeTime;
        this.path = path;
        this.storageTime = storageTime;
        this.folderName = folderName;
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

    @NonNull
    public String getFolderName() {
        return folderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookmarkEntity bookmarkEntity = (BookmarkEntity) o;
        return id == bookmarkEntity.id &&
                folderName == bookmarkEntity.folderName &&
                originalWebUrl.equals(bookmarkEntity.originalWebUrl) &&
                simpleWebUrl.equals(bookmarkEntity.simpleWebUrl) &&
                mainImageUrl.equals(bookmarkEntity.mainImageUrl) &&
                title.equals(bookmarkEntity.title) &&
                author.equals(bookmarkEntity.author) &&
                writeTime.equals(bookmarkEntity.writeTime) &&
                path.equals(bookmarkEntity.path) &&
                storageTime.equals(bookmarkEntity.storageTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, originalWebUrl, simpleWebUrl, mainImageUrl, title, author, writeTime, path, storageTime, folderName);
    }

    public static class Builder {
        private int id;
        private String originalWebUrl;
        private String simpleWebUrl;
        private String mainImageUrl;
        private String title;
        private String author;
        private String writeTime;
        private String path;
        private String storageTime;
        private String folderName;

        public Builder fromResponse(@NonNull final NewBookmarkResponse response, @NonNull final String path) {
            this.id = 0;
            this.originalWebUrl = response.getOriginalweburl();
            this.simpleWebUrl = response.getSimpleweburl();
            this.mainImageUrl = response.getMainimage();
            this.title = response.getTitle();
            this.author = response.getAuthor();
            this.writeTime = response.getWritetime();
            this.path = path;
            this.storageTime = response.getStoragetime();
            this.folderName = response.getFolderName();

            return this;
        }

        public BookmarkEntity build() {
            return new BookmarkEntity(id,
                    originalWebUrl,
                    simpleWebUrl,
                    mainImageUrl,
                    title,
                    author,
                    writeTime,
                    path,
                    storageTime,
                    folderName);
        }
    }
}
