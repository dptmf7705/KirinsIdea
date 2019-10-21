package com.kirinsidea.data.source.local.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
}
