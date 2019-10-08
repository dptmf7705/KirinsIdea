package com.kirinsidea.data.source.local.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 메모
 *
 * @member id           메모 ID (PK)
 * @member startIndex   시작 인덱스
 * @member endIndex     끝 인덱스
 * @member text         문구
 * @member memo         메모 내용
 * @member bookmarkId   북마크 ID
 */
@Entity(tableName = "memo")
public class Memo {
    @PrimaryKey(autoGenerate = true)
    private final int id;
    private final long startIndex;
    private final long endIndex;
    @NonNull
    private final String text;
    @NonNull
    private final String memo;
    private final int bookmarkId;

    public Memo(final int id,
                final long startIndex,
                final long endIndex,
                @NonNull final String text,
                @NonNull final String memo,
                final int bookmarkId) {
        this.id = id;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.text = text;
        this.memo = memo;
        this.bookmarkId = bookmarkId;
    }

    public int getId() {
        return id;
    }

    public long getStartIndex() {
        return startIndex;
    }

    public long getEndIndex() {
        return endIndex;
    }

    @NonNull
    public String getText() {
        return text;
    }

    @NonNull
    public String getMemo() {
        return memo;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }
}
