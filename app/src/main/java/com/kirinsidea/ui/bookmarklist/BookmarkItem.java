package com.kirinsidea.ui.bookmarklist;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.BookmarkEntity;

import java.util.Objects;

public class BookmarkItem {
    private final int id;
    @NonNull
    private final String simpleWebUrl;
    @NonNull
    private final String mainImageUrl;
    @NonNull
    private final String title;
    @NonNull
    private final String storageTime;

    public BookmarkItem(final int id,
                        @NonNull final String simpleWebUrl,
                        @NonNull final String mainImageUrl,
                        @NonNull final String title,
                        @NonNull final String storageTime) {
        this.id = id;
        this.simpleWebUrl = simpleWebUrl;
        this.mainImageUrl = mainImageUrl;
        this.title = title;
        this.storageTime = storageTime;
    }

    public int getId() {
        return id;
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
    public String getStorageTime() {
        return storageTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookmarkItem that = (BookmarkItem) o;
        return id == that.id &&
                simpleWebUrl.equals(that.simpleWebUrl) &&
                mainImageUrl.equals(that.mainImageUrl) &&
                title.equals(that.title) &&
                storageTime.equals(that.storageTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, simpleWebUrl, mainImageUrl, title, storageTime);
    }

    public static class Builder {
        private int id;
        private String simpleWebUrl;
        private String mainImageUrl;
        private String title;
        private String storageTime;

        @NonNull
        public Builder fromEntity(@NonNull final BookmarkEntity entity) {
            this.id = entity.getId();
            this.simpleWebUrl = entity.getSimpleWebUrl();
            this.mainImageUrl = entity.getMainImageUrl();
            this.title = entity.getTitle();
            this.storageTime = entity.getStorageTime();
            return this;
        }

        @NonNull
        public BookmarkItem build() {
            return new BookmarkItem(id,
                    simpleWebUrl,
                    mainImageUrl,
                    title,
                    storageTime);
        }
    }
}
