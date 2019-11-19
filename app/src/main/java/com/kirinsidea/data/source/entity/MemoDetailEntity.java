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
    private String memoId;
    private String bookmarkId;
    private String highlightId;
    private String startIndex;
    private String endIndex;
    private String highlightText;
    private String highlightColor;
    private String memoContent;
    private boolean isPrivate;

    public String getMemoId() {
        return memoId;
    }

    public void setMemoId(String memoId) {
        this.memoId = memoId;
    }

    public String getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(String bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getHighlightId() {
        return highlightId;
    }

    public void setHighlightId(String highlightId) {
        this.highlightId = highlightId;
    }

    public String getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(String startIndex) {
        this.startIndex = startIndex;
    }

    public String getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(String endIndex) {
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
