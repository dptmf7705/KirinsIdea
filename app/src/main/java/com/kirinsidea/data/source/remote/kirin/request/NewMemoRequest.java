package com.kirinsidea.data.source.remote.kirin.request;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewMemoRequest {
    private final int startIndex;
    private final int endIndex;
    @NonNull
    private final String text;
    @Nullable
    private final String memo;
    private final int bookmarkId;

    public NewMemoRequest(final int startIndex,
                          final int endIndex,
                          @NonNull final String text,
                          @Nullable final String memo,
                          final int bookmarkId) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.text = text;
        this.memo = memo;
        this.bookmarkId = bookmarkId;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    @NonNull
    public String getText() {
        return text;
    }

    @Nullable
    public String getMemo() {
        return memo;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    @Override
    public String toString() {
        return "NewMemoRequest{" +
                "startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", text='" + text + '\'' +
                ", memo='" + memo + '\'' +
                ", bookmarkId=" + bookmarkId +
                '}';
    }
}
