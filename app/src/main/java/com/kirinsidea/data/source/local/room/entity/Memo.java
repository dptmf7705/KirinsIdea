package com.kirinsidea.data.source.local.room.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;

import java.util.Objects;

/**
 * 메모
 *
 * @member startIndex   시작 인덱스 (PK)
 * @member endIndex     끝 인덱스 (PK)
 * @member text         문구
 * @member memo         메모 내용
 * @member bookmarkId   북마크 ID (PK)
 */
@Entity(tableName = "memo", primaryKeys = {"startIndex", "endIndex", "bookmarkId"})
public class Memo {
    private final int startIndex;
    private final int endIndex;
    @NonNull
    private final String text;
    @Nullable
    private final String memo;
    private final int bookmarkId;

    public Memo(final int startIndex,
                final int endIndex,
                @NonNull final String text,
                @Nullable final String memo,
                final int bookmarkId) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.text = text;
        this.memo = memo;
        this.bookmarkId = bookmarkId;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    @NonNull
    public String getText() {
        return text;
    }

    @Nullable
    public String getMemo() {
        return memo;
    }

    public int getBookmarkId() {
        return bookmarkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Memo memo1 = (Memo) o;
        return startIndex == memo1.startIndex &&
                endIndex == memo1.endIndex &&
                bookmarkId == memo1.bookmarkId &&
                text.equals(memo1.text) &&
                Objects.equals(memo, memo1.memo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startIndex, endIndex, text, memo, bookmarkId);
    }

    @Override
    public String toString() {
        return "Memo{" +
                "startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", text='" + text + '\'' +
                ", memo='" + memo + '\'' +
                ", bookmarkId=" + bookmarkId +
                '}';
    }
}
