package com.kirinsidea.ui.highlight;

import android.util.Pair;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.HighlightEntity;

public class Highlight {
    private static final int NEW_HIGHLIGHT_ID = 0;
    private final int highlightId;
    private final int bookmarkId;
    @NonNull
    private Pair<Integer, Integer> selection;
    @NonNull
    private String highlightText;
    @NonNull
    private HighlightColor highlightColor;

    public Highlight(final int bookmarkId,
                     @NonNull final Pair<Integer, Integer> selection,
                     @NonNull final String highlightText,
                     @NonNull final HighlightColor highlightColor) {
        this.highlightId = NEW_HIGHLIGHT_ID;
        this.bookmarkId = bookmarkId;
        this.selection = selection;
        this.highlightText = highlightText;
        this.highlightColor = highlightColor;
    }

    private Highlight(@NonNull final Builder builder) {
        this.highlightId = builder.highlightId;
        this.bookmarkId = builder.bookmarkId;
        this.selection = builder.selection;
        this.highlightText = builder.selectedText;
        this.highlightColor = builder.highlightColor;
    }

    public int getHighlightId() {
        return highlightId;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    @NonNull
    public Pair<Integer, Integer> getSelection() {
        return selection;
    }

    public void setSelection(@NonNull Pair<Integer, Integer> selection) {
        this.selection = selection;
    }

    @NonNull
    public String getHighlightText() {
        return highlightText;
    }

    public void setHighlightText(@NonNull String highlightText) {
        this.highlightText = highlightText;
    }

    @NonNull
    public HighlightColor getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(@NonNull HighlightColor highlightColor) {
        this.highlightColor = highlightColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Highlight highlight = (Highlight) o;
        return highlightId == highlight.highlightId &&
                bookmarkId == highlight.bookmarkId &&
                selection.equals(highlight.selection) &&
                highlightText.equals(highlight.highlightText) &&
                highlightColor == highlight.highlightColor;
    }

    @Override
    public String toString() {
        return "Highlight{" +
                "highlightId=" + highlightId +
                ", bookmarkId=" + bookmarkId +
                ", selection=" + selection +
                ", highlightText='" + highlightText + '\'' +
                ", highlightColor=" + highlightColor +
                '}';
    }

    public static class Builder {
        private final int highlightId;
        private final int bookmarkId;
        @NonNull
        private final Pair<Integer, Integer> selection;
        @NonNull
        private final String selectedText;
        @NonNull
        private final HighlightColor highlightColor;

        public Builder(@NonNull final HighlightEntity entity) {
            this.highlightId = entity.getHighlightId();
            this.bookmarkId = entity.getBookmarkId();
            this.selection = new Pair<>(entity.getStartIndex(), entity.getEndIndex());
            this.selectedText = entity.getHighlightText();
            this.highlightColor = HighlightColor.valueOf(entity.getHighlightColor());
        }

        public Highlight build() {
            return new Highlight(this);
        }
    }
}
