package com.kirinsidea.data.source.remote.kirin.mock;

import com.kirinsidea.data.source.entity.BookmarkEntity;
import com.kirinsidea.data.source.remote.kirin.api.BookmarkApi;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitResultCode;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.data.source.remote.kirin.response.SingleResponse;
import com.kirinsidea.ui.bookmark.Bookmark;
import com.kirinsidea.ui.bookmarklist.BookmarkItem;
import com.kirinsidea.utils.DateUtil;

import java.util.Date;
import java.util.Random;

import io.reactivex.Single;

public class BookmarkMockApi implements BookmarkApi {

    @Override
    public Single<SingleResponse<BookmarkEntity>> addNewBookmark(NewBookmarkRequest request) {
        BookmarkEntity bookmark = new BookmarkEntity.Builder(new Bookmark("1", "https://search.naver.com/search.naver?where=nexearch&query=%EB%8B%A5%ED%84%B0%ED%93%A8%EB%A6%AC+%EB%AF%B8%EC%84%B8%EB%A8%BC%EC%A7%80+%EB%A7%88%EC%8A%A4%ED%81%AC&sm=top_lve&ie=utf8"
                , "https://naver.com", "얄루", "지은이", "2019-10-25 20:46:00", "<html>\n" +
                "<head>\n" +
                "  <title>HTML - 수업소개</title>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1><a href=\"index.html\">HTML</a></h1>\n" +
                "<ol>\n" +
                "  <li><a href=\"1.html\">기술소개</a></li>\n" +
                "  <li><a href=\"2.html\">기본문법<a/></li>\n" +
                "  <li><a href=\"3.html\">하이퍼텍스트와 속성</a></li>\n" +
                "  <li><a href=\"4.html\">리스트와 태그의 중첩</a></li>\n" +
                "</ol>\n" +
                " \n" +
                "<h2>선행학습</h2>\n" +
                " \n" +
                "본 수업을 효과적으로 수행하기 위해서는 웹애플리케이션에 대한 전반적인 이해가 필요합니다. 이를 위해서 준비된 수업은 아래 링크를 통해서 접근하실 수 있습니다. \n" +
                "</body>\n" +
                "</html>")).build();
        BookmarkEntity bookmarkItem = new BookmarkEntity.Builder(new BookmarkItem(String.valueOf(new Random().nextInt()),
                "https://naver.com", "http://www.newsian.co.kr/news/photo/201908/36256_15176_2729.png"
                , "얄루", DateUtil.getCurrentDateTime(), request.getFolderId())).build();
        SingleResponse<BookmarkEntity> response = new SingleResponse<>(RetrofitResultCode.SUCCESS, bookmark);
        SingleResponse<BookmarkEntity> response1 = new SingleResponse<>(RetrofitResultCode.SUCCESS, bookmarkItem);

        return Single.just(response1);
    }
}
