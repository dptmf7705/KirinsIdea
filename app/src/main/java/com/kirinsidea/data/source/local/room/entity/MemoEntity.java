package com.kirinsidea.data.source.local.room.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 메모
 *
 * @member id           ID (PK)
 * @member highlightId  하이라이트 ID
 * @member content      메모 내용
 * @member isPrivate    true(나만보기) / false(전체공개)
 */
@Entity(tableName = "memo")
public class MemoEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int highlightId;
    private String content;
    private boolean isPrivate;

    public MemoEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHighlightId() {
        return highlightId;
    }

    public void setHighlightId(int highlightId) {
        this.highlightId = highlightId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
