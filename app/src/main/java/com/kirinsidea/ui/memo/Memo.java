package com.kirinsidea.ui.memo;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.entity.MemoDetailEntity;

public class Memo {
    private static final String NEW_MEMO_ID = "0";
    private String memoId;
    private final String bookmarkId;
    private final String highlightId;
    private final String startIndex;
    private final String endIndex;
    private final String highlightText;
    private String memoContent;
    private boolean isPrivate;

    public Memo(final String bookmarkId,
                final String highlightId,
                final String startIndex,
                final String endIndex,
                @NonNull final String highlightText,
                @NonNull final String memoContent) {
        this.memoId = NEW_MEMO_ID;
        this.bookmarkId = bookmarkId;
        this.highlightId = highlightId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.highlightText = highlightText;
        this.memoContent = memoContent;
        this.isPrivate = false;
    }

    private Memo(Builder builder) {
        this.memoId = builder.memoId;
        this.bookmarkId = builder.bookmarkId;
        this.highlightId = builder.highlightId;
        this.startIndex = builder.startIndex;
        this.endIndex = builder.endIndex;
        this.highlightText = builder.highlightText;
        this.memoContent = builder.memoContent;
        this.isPrivate = builder.isPrivate;
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

    public String getStartIndex() {
        return startIndex;
    }

    public String getEndIndex() {
        return endIndex;
    }

    public String getHighlightText() {
        return highlightText;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "memoId=" + memoId +
                ", bookmarkId=" + bookmarkId +
                ", highlightId=" + highlightId +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", highlightText='" + highlightText + '\'' +
                ", memoContent='" + memoContent + '\'' +
                ", isPrivate=" + isPrivate +
                '}';
    }

    public static class Builder {
        private final String memoId;
        private final String bookmarkId;
        private final String highlightId;
        private final String startIndex;
        private final String endIndex;
        private final String highlightText;
        private final String memoContent;
        private final boolean isPrivate;

        private Builder(@NonNull final MemoDetailEntity entity) {
            this.memoId = entity.getMemoId();
            this.bookmarkId = entity.getBookmarkId();
            this.highlightId = entity.getHighlightId();
            this.startIndex = entity.getStartIndex();
            this.endIndex = entity.getEndIndex();
            this.highlightText = entity.getHighlightText();
            this.memoContent = entity.getMemoContent();
            this.isPrivate = entity.isPrivate();
        }

        public static Builder with(@NonNull final MemoDetailEntity model) {
            return new Builder(model);
        }

        public Memo build() {
            return new Memo(this);
        }
    }
}
