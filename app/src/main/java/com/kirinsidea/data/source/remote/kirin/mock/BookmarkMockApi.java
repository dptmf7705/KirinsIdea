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

import io.reactivex.Single;

public class BookmarkMockApi implements BookmarkApi {

    @Override
    public Single<SingleResponse<BookmarkEntity>> addNewBookmark(NewBookmarkRequest request) {
        BookmarkEntity bookmark = new BookmarkEntity.Builder(new Bookmark("0", "https://search.naver.com/search.naver?where=nexearch&query=%EB%8B%A5%ED%84%B0%ED%93%A8%EB%A6%AC+%EB%AF%B8%EC%84%B8%EB%A8%BC%EC%A7%80+%EB%A7%88%EC%8A%A4%ED%81%AC&sm=top_lve&ie=utf8"
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
        BookmarkEntity bookmarkItem = new BookmarkEntity.Builder(new BookmarkItem("0",
                "https://naver.com", "https://adcr.naver.com/adcr?x=fVmUp2v/RDLhlzjzLtKnQf///w==kSANdnnR+Rhnm1B+R0hXTqKRjazn6K1HmsXxoCUllpHirSVZEI/kS4ct/FdLTmWxco7grlnH5+iW7/FlsKeR4DWyDRGRo6OOWXmG6vA/9MOg8z8ejSh59zREambBoHV3DKbAfiHVwkJ2xz6gLCCvJXCeXdfepqwbvBF4k/rI8DJQvEMMIWCtTnaXiCBgYtgsG6nW93HG+7ukuGIDjVP0CsNIX8+lf1Dr53ZTYip9OsdtST8aJblu7CcWYljnAHCy9C6zPFplg17OKVIAtTqNIdx8SmkgoLENKdHvzBgSag+jHD1FGf9YP9+Q5Z8WODoNfthZ714qhctTUWeXwqe7OvWnq+b/T0cbEQwGMoLGmdLosrjleh1RgA16fJgf3m4zsQ+rAC8X8t6E1Y9dokd9U/oPtUsVBHppXnDDswhTQgKKZdc+BHpFivW+lk3bQMJAsHiy681D/NTS3eW/INDSWC4Z2d+LgR8sBLhK2JOfvRvcssaqq2KFzaArwoVg+LTkLGXskj8naJHIKuaIaLt/e1IZ+pNZWkmT1+zXVV6SQNaI/hJTnXL8A1E49Hdvz4ig+rpDOIbNhhlj7fSBEj61vaA5sfO2rtuNcuSepRtlOaCJCuxmrgozEVClvN3ZpQWHS"
                , "얄루", DateUtil.getCurrentDateTime(), request.getFolderId())).build();
        SingleResponse<BookmarkEntity> response = new SingleResponse<>(RetrofitResultCode.SUCCESS, bookmark);
        SingleResponse<BookmarkEntity> response1 = new SingleResponse<>(RetrofitResultCode.SUCCESS, bookmarkItem);

        return Single.just(response1);
    }
}
