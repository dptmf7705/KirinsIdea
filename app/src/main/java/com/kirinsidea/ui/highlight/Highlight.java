package com.kirinsidea.ui.highlight;

import androidx.annotation.NonNull;

public class Highlight {
    private final int id;
    private final int bookmarkId;
    private int startIndex;
    private int endIndex;
    private String text;
    private HighlightColor color;

    public Highlight(final int id,
                     final int bookmarkId,
                     final int startIndex,
                     final int endIndex,
                     @NonNull final String text,
                     final HighlightColor color) {
        this.id = id;
        this.bookmarkId = bookmarkId;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.text = text;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public int getBookmarkId() {
        return bookmarkId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HighlightColor getColor() {
        return color;
    }

    public void setColor(HighlightColor color) {
        this.color = color;
    }
}
