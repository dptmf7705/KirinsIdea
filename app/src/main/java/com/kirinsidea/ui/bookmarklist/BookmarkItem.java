package com.kirinsidea.ui.bookmarklist;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.kirinsidea.data.source.entity.BookmarkEntity;

import java.util.Objects;

public class BookmarkItem {
    @NonNull
    private final String id;
    @NonNull
    private final String simpleWebUrl;
    @NonNull
    private final String mainImageUrl;
    @NonNull
    private final String title;
    @NonNull
    private final String storageTime;
    @NonNull
    private final String folderId;

    public BookmarkItem(@NonNull final String id,
                        @NonNull final String simpleWebUrl,
                        @NonNull final String mainImageUrl,
                        @NonNull final String title,
                        @NonNull final String storageTime,
                        @NonNull final String folderId) {
        this.id = id;
        this.simpleWebUrl = simpleWebUrl;
        this.mainImageUrl = mainImageUrl;
        this.title = title;
        this.storageTime = storageTime;
        this.folderId = folderId;
    }

    @NonNull
    public String getId() {
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

    @NonNull
    public String getFolderId() {
        return folderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookmarkItem that = (BookmarkItem) o;
        return id.equals(that.id) &&
                simpleWebUrl.equals(that.simpleWebUrl) &&
                mainImageUrl.equals(that.mainImageUrl) &&
                title.equals(that.title) &&
                storageTime.equals(that.storageTime) &&
                folderId.equals(that.folderId);
    }

    @Override
    public int  hashCode() {
        return Objects.hash(id, simpleWebUrl, mainImageUrl, title, storageTime, folderId);
    }

    public static class Builder {
        private String id;
        private String simpleWebUrl;
        private String mainImageUrl;
        private String title;
        private String storageTime;
        private String folderId;

        @NonNull
        public Builder fromEntity(@NonNull final BookmarkEntity entity) {
            this.id = entity.getId();
            this.simpleWebUrl = entity.getSimpleWebUrl();
            this.mainImageUrl = entity.getMainImageUrl();
            this.title = entity.getTitle();
            this.storageTime = entity.getStorageTime();
            this.folderId = entity.getFolderId();
            return this;
        }

        @NonNull
        public BookmarkItem build() {
            return new BookmarkItem(id,
                    simpleWebUrl,
                    mainImageUrl,
                    title,
                    storageTime,
                    folderId);
        }
    }

    public static final DiffUtil.ItemCallback<BookmarkItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<BookmarkItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull BookmarkItem oldItem, @NonNull BookmarkItem newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(@NonNull BookmarkItem oldItem, @NonNull BookmarkItem newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
