package com.kirinsidea.ui.highlight;

import android.util.Pair;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.HighlightEntity;

public class Highlight {
    private int id;
    private int bookmarkId;
    @NonNull
    private Pair<Integer, Integer> selection = new Pair<>(0, 0);
    @NonNull
    private String selectedText = "";
    @NonNull
    private HighlightColor color = HighlightColor.Yellow;

    public Highlight() {
    }

    public Highlight(final int id,
                     final int bookmarkId,
                     @NonNull final Pair<Integer, Integer> selection,
                     @NonNull final String selectedText,
                     @NonNull final HighlightColor color) {
        this.id = id;
        this.bookmarkId = bookmarkId;
        this.selection = selection;
        this.selectedText = selectedText;
        this.color = color;
    }

    private Highlight(@NonNull final Builder builder) {
        this.id = builder.id;
        this.bookmarkId = builder.bookmarkId;
        this.selection = builder.selection;
        this.selectedText = builder.selectedText;
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

    @NonNull
    public Pair<Integer, Integer> getSelection() {
        return selection;
    }

    public void setSelection(@NonNull Pair<Integer, Integer> selection) {
        this.selection = selection;
    }

    @NonNull
    public String getSelectedText() {
        return selectedText;
    }

    public void setSelectedText(@NonNull String selectedText) {
        this.selectedText = selectedText;
    }

    @NonNull
    public HighlightColor getColor() {
        return color;
    }

    public void setColor(@NonNull HighlightColor color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Highlight highlight = (Highlight) o;
        return id == highlight.id &&
                bookmarkId == highlight.bookmarkId &&
                selection.equals(highlight.selection) &&
                selectedText.equals(highlight.selectedText) &&
                color == highlight.color;
    }

    public static class Builder {
        private final int id;
        private final int bookmarkId;
        @NonNull
        private final Pair<Integer, Integer> selection;
        @NonNull
        private final String selectedText;
        @NonNull
        private final HighlightColor color;

        public Builder(@NonNull final HighlightEntity entity) {
            this.id = entity.getId();
            this.bookmarkId = entity.getBookmarkId();
            this.selection = new Pair<>(entity.getStartIndex(), entity.getEndIndex());
            this.selectedText = entity.getText();
            this.color = HighlightColor.valueOf(entity.getColor());
        }

        public Highlight build() {
            return new Highlight(this);
        }
    }
}
