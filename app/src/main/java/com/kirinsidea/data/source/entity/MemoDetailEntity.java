package com.kirinsidea.data.source.entity;

/**
 * 메모 + 하이라이트 조인 엔티티
 *
 * @member memoId           메모 ID (PK)
 * @member bookmarkId       북마크 ID
 * @member highlightId      하이라이트 ID
 * @member startIndex       시작 인덱스
 * @member endIndex         끝 인덱스
 * @member highlightText    하이라이트 문구
 * @member highlightColor   하이라이트 색깔
 * @member memoContent      메모 내용
 * @member isPrivate        true: 나만보기 / false: 전체공개
 */
public class MemoDetailEntity {
    private int memoId;
    private String bookmarkId;
    private int highlightId;
    private int startIndex;
    private int endIndex;
    private String highlightText;
    private String highlightColor;
    private String memoContent;
    private boolean isPrivate;

    public int getMemoId() {
        return memoId;
    }

    public void setMemoId(int memoId) {
        this.memoId = memoId;
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(String bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public int getHighlightId() {
        return highlightId;
    }

    public void setHighlightId(int highlightId) {
        this.highlightId = highlightId;
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
}
