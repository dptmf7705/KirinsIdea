package com.kirinsidea.data.source.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.kirinsidea.ui.memo.Memo;
import com.kirinsidea.ui.memo.NewMemo;

/**
 * 메모
 *
 * @member memoId       메모 ID (PK)
 * @member highlightId  하이라이트 ID
 * @member title        메모 제목 (하이라이트 문구)
 * @member memoContent  메모 내용
 * @member isPrivate    true: 나만보기 / false: 전체공개
 */
@Entity(tableName = "memo")
public class MemoEntity {
    @PrimaryKey(autoGenerate = true)
    private int memoId;
    private int highlightId;
    private String memoContent;
    private boolean isPrivate;

    public MemoEntity() {
    }

    private MemoEntity(Builder builder) {
        this.memoId = builder.memoId;
        this.highlightId = builder.highlightId;
        this.memoContent = builder.memoContent;
        this.isPrivate = builder.isPrivate;
    }

    public int getMemoId() {
        return memoId;
    }

    public void setMemoId(int memoId) {
        this.memoId = memoId;
    }

    public int getHighlightId() {
        return highlightId;
    }

    public void setHighlightId(int highlightId) {
        this.highlightId = highlightId;
    }

    public String getMemoContent() {
        return memoContent;
    }

    public void setMemoContent(String memoContent) {
        this.memoContent = memoContent;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public static class Builder {
        private final int memoId;
        private final int highlightId;
        private final String memoContent;
        private final boolean isPrivate;

        private Builder(@NonNull final Memo model) {
            this.memoId = model.getMemoId();
            this.highlightId = model.getHighlightId();
            this.memoContent = model.getMemoContent();
            this.isPrivate = model.isPrivate();
        }

        public Builder(@NonNull final NewMemo model) {
            this.memoId = model.getMemoId();
            this.highlightId = model.getHighlightId();
            this.memoContent = model.getMemoContent();
            this.isPrivate = model.isPrivate();
        }

        public static Builder with(@NonNull final Memo model) {
            return new Builder(model);
        }

        public static Builder with(@NonNull final NewMemo model) {
            return new Builder(model);
        }

        public MemoEntity build() {
            return new MemoEntity(this);
        }
    }
}
