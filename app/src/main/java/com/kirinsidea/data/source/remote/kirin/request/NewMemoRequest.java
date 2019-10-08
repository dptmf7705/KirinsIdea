package com.kirinsidea.data.source.remote.kirin.request;

import androidx.annotation.NonNull;

public class NewMemoRequest {
    private final long startIndex;
    private final long endIndex;
    @NonNull
    private final String text;
    @NonNull
    private final String memo;
    private final int bookmarkId;

    public NewMemoRequest(final long startIndex,
                          final long endIndex,
                          @NonNull final String text,
                          @NonNull final String memo,
                          final int bookmarkId) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.text = text;
        this.memo = memo;
        this.bookmarkId = bookmarkId;
    }

    public long getStartIndex() {
        return startIndex;
    }

    public long getEndIndex() {
        return endIndex;
    }

    @NonNull
    public String getText() {
        return text;
    }

    @NonNull
    public String getMemo() {
        return memo;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }
}
