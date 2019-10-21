package com.kirinsidea.ui.highlight;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.HighlightEntity;

public class Highlight {
    private int id;
    private int bookmarkId;
    private int startIndex;
    private int endIndex;
    @NonNull
    private String text = "";
    @NonNull
    private HighlightColor color = HighlightColor.Yellow;

    public Highlight() {
    }

    public Highlight(final int id,
                     final int bookmarkId,
                     final int startIndex,
                     final int endIndex,
                     @NonNull final String text,
                     @NonNull final HighlightColor color) {
        this.id = id;
        this.bookmarkId = bookmarkId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.text = text;
        this.color = color;
    }

    private Highlight(@NonNull final Builder builder) {
        this.id = builder.id;
        this.bookmarkId = builder.bookmarkId;
        this.startIndex = builder.startIndex;
        this.endIndex = builder.endIndex;
        this.text = builder.text;
        this.color = builder.color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(int bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    @NonNull
    public HighlightColor getColor() {
        return color;
    }

    public void setColor(@NonNull HighlightColor color) {
        this.color = color;
    }

    public static class Builder {
        private final int id;
        private final int bookmarkId;
        private final int startIndex;
        private final int endIndex;
        @NonNull
        private final String text;
        @NonNull
        private final HighlightColor color;

        public Builder(@NonNull final HighlightEntity entity) {
            this.id = entity.getId();
            this.bookmarkId = entity.getBookmarkId();
            this.startIndex = entity.getStartIndex();
            this.endIndex = entity.getEndIndex();
            this.text = entity.getText();
            this.color = HighlightColor.valueOf(entity.getColor());
        }

        public Highlight build() {
            return new Highlight(this);
        }
    }
}
