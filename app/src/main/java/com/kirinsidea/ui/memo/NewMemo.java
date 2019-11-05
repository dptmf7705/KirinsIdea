package com.kirinsidea.ui.memo;

import androidx.annotation.NonNull;

public class NewMemo {
    private static final int NEW_MEMO_ID = 0;
    private final int memoId = NEW_MEMO_ID;
    private final int bookmarkId;
    private final int highlightId;
    private final String memoContent;
    private final boolean isPrivate = false;

    public NewMemo(final int bookmarkId,
                   final int highlightId,
                   @NonNull final String memoContent) {
        this.bookmarkId = bookmarkId;
        this.highlightId = highlightId;
        this.memoContent = memoContent;
    }

    public int getMemoId() {
        return memoId;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    public int getHighlightId() {
        return highlightId;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
