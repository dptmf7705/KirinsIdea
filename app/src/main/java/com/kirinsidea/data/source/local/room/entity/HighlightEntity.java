package com.kirinsidea.data.source.local.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kirinsidea.ui.highlight.Highlight;

/**
 * 하이라이트
 *
 * @member id           ID (PK)
 * @member bookmarkId   북마크 ID
 * @member startIndex   시작 인덱스
 * @member endIndex     끝 인덱스
 * @member text         하이라이트 문구
 * @member color        하이라이트 색깔
 */
@Entity(tableName = "highlight")
public class HighlightEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int bookmarkId;
    private int startIndex;
    private int endIndex;
    private String text;
    private String color;

    public HighlightEntity() {
    }

    private HighlightEntity(Builder builder) {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static class Builder {
        private final int id;
        private final int bookmarkId;
        private final int startIndex;
        private final int endIndex;
        private final String text;
        private final String color;

        public Builder(@NonNull final Highlight model) {
            this.id = model.getId();
            this.bookmarkId = model.getBookmarkId();
            this.startIndex = model.getStartIndex();
            this.endIndex = model.getEndIndex();
            this.text = model.getText();
            this.color = model.getColor().getColorName();
        }

        public HighlightEntity build() {
            return new HighlightEntity(this);
        }
    }
}
