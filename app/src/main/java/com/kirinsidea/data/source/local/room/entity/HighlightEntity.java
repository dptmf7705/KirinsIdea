package com.kirinsidea.data.source.local.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kirinsidea.ui.highlight.Highlight;

/**
 * 하이라이트
 *
 * @member highlightId      하이라이트 ID (PK)
 * @member bookmarkId       북마크 ID
 * @member startIndex       시작 인덱스
 * @member endIndex         끝 인덱스
 * @member highlightText    하이라이트 문구
 * @member highlightColor   하이라이트 색깔
 */
@Entity(tableName = "highlight")
public class HighlightEntity {
    @PrimaryKey(autoGenerate = true)
    private int highlightId;
    private int bookmarkId;
    private int startIndex;
    private int endIndex;
    private String highlightText;
    private String highlightColor;

    public HighlightEntity() {
    }

    private HighlightEntity(Builder builder) {
        this.highlightId = builder.highlightId;
        this.bookmarkId = builder.bookmarkId;
        this.startIndex = builder.startIndex;
        this.endIndex = builder.endIndex;
        this.highlightText = builder.highlightText;
        this.highlightColor = builder.highlightColor;
    }

    public int getHighlightId() {
        return highlightId;
    }

    public void setHighlightId(int highlightId) {
        this.highlightId = highlightId;
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

    public String getHighlightText() {
        return highlightText;
    }

    public void setHighlightText(String highlightText) {
        this.highlightText = highlightText;
    }

    public String getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(String highlightColor) {
        this.highlightColor = highlightColor;
    }

    public static class Builder {
        private final int highlightId;
        private final int bookmarkId;
        private final int startIndex;
        private final int endIndex;
        private final String highlightText;
        private final String highlightColor;

        public Builder(@NonNull final Highlight model) {
            this.highlightId = model.getHighlightId();
            this.bookmarkId = model.getBookmarkId();
            this.startIndex = model.getSelection().first;
            this.endIndex = model.getSelection().second;
            this.highlightText = model.getHighlightText();
            this.highlightColor = model.getHighlightColor().getColorName();
        }

        public HighlightEntity build() {
            return new HighlightEntity(this);
        }
    }
}
