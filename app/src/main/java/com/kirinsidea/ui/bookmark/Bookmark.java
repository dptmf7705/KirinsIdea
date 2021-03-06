package com.kirinsidea.ui.bookmark;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.data.source.entity.BookmarkEntity;

public class Bookmark {
    @NonNull
    private final String id;
    @NonNull
    private final String originalWebUrl;
    @NonNull
    private final String simpleWebUrl;
    @NonNull
    private final String title;
    @NonNull
    private final String author;
    @NonNull
    private final String writeTime;
    @Nullable
    private final CharSequence contents;

    public Bookmark(@NonNull final String id,
                    @NonNull final String originalWebUrl,
                    @NonNull final String simpleWebUrl,
                    @NonNull final String title,
                    @NonNull final String author,
                    @NonNull final String writeTime,
                    @Nullable final CharSequence contents) {
        this.id = id;
        this.originalWebUrl = originalWebUrl;
        this.simpleWebUrl = simpleWebUrl;
        this.title = title;
        this.author = author;
        this.writeTime = writeTime;
        this.contents = contents;
    }

    @NonNull
    public String getId() {
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

    @Nullable
    public CharSequence getContents() {
        return contents;
    }

    public static class Builder {
        private String id;
        private String originalWebUrl;
        private String simpleWebUrl;
        private String title;
        private String author;
        private String writeTime;
        private CharSequence contents;

        @NonNull
        public Builder fromEntity(@NonNull final BookmarkEntity entity) {
            this.id = entity.getId();
            this.originalWebUrl = entity.getOriginalWebUrl();
            this.simpleWebUrl = entity.getSimpleWebUrl();
            this.title = entity.getTitle();
            this.author = entity.getAuthor();
            this.writeTime = entity.getWriteTime();
            return this;
        }

        @NonNull
        public Builder setContents(@Nullable final CharSequence contents) {
            this.contents = contents;
            return this;
        }

        @NonNull
        public Bookmark build() {
            return new Bookmark(id,
                    originalWebUrl,
                    simpleWebUrl,
                    title,
                    author,
                    writeTime,
                    contents);
        }
    }
}
