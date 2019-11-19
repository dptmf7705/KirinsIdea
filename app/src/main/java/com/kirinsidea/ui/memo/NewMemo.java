package com.kirinsidea.ui.memo;

import androidx.annotation.NonNull;

public class NewMemo {
    private static final String NEW_MEMO_ID = "0";
    private final String memoId = NEW_MEMO_ID;
    private final String bookmarkId;
    private final String highlightId;
    private final String memoContent;
    private final boolean isPrivate = false;

    public NewMemo(final String bookmarkId,
                   final String highlightId,
                   @NonNull final String memoContent) {
        this.bookmarkId = bookmarkId;
        this.highlightId = highlightId;
        this.memoContent = memoContent;
    }

    public String getMemoId() {
        return memoId;
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public String getHighlightId() {
        return highlightId;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
