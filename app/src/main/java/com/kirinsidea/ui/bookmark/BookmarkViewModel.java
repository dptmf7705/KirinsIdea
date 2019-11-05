package com.kirinsidea.ui.bookmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.ui.BaseViewModel;
import com.kirinsidea.utils.DateUtil;

public class BookmarkViewModel extends BaseViewModel {
    @NonNull
    private final MutableLiveData<Bookmark> bookmark = new MutableLiveData<>();

    private BookmarkRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull final BaseRepository... repositories) {
        this.repository = (BookmarkRepository) repositories[0];
        return this;
    }

    void loadBookmark(final int bookmarkId) {
        bookmark.setValue(new Bookmark(0,
                "https://publy.co/",
                "https://publy.co/",
                "러쉬(Lush), 진정성의 가치를 고수하다",
                "위클리비즈",
                DateUtil.getCurrentDateTime(),
                "<p><img src=\"https://user-images.githubusercontent.com/22764812/67147641-88e05280-f2d1-11e9-9239-fd85f6a81e63.png\"/></p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p><b>우리는 즐거움과 만족감을 팝니다</b></p>\n" +
                        "<p>흔히 화장품 회사는 '꿈을 파는 회사'로 통한다. 화장품 매장은 세련되고 깔끔한 인테리어에 고급스러운 분위기가 물씬 풍긴다. 제품은 근사한 디자인의 유리 케이스에 100ml 정도만 담겨 있고, 제품의 이름에는 '아름다운', '꽃이 피는' 같은 미사여구가 잔뜩 붙어 있다.</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>그런데 여기 조금 다른 화장품 회사가 있다. 이 회사의 매장은 마치 과일 가게 같다. 입욕제나 비누, 마사지바, 팩 같은 제품들을 포장 하나 없이 날것 그대로 진열해놓았다.</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>꺼끌꺼끌한 비누가 덩어리째 매대 위에 올려져 있고, 사려면 덩어리를 원하는 양만큼 잘라 재활용 종이에 둘둘 말아 건네준다. 액체 화장품은 플라스틱 용기에 담지만, 그도 이중&middot;삼중의 포장 없이 그냥 용기째로 판매한다.</p>\n" +
                        "<p><img src=\"https://user-images.githubusercontent.com/22764812/67147642-8da50680-f2d1-11e9-97dc-e97d8191c1d2.png\"/></p>\n" +
                        "<p>러쉬 제품은 포장지로 싸지 않고 민낯 그대로 진열하는 게 많다. 목욕할 때 사용하는 입욕제 '발리스틱'이 상자에 가득 담겨 있다. &copy;러쉬</p>\n" +
                        "<p>이런 풍경은 이 회사, 러쉬의 정신을 잘 나타낸다. 억지로 예뻐 보이려 애쓰기보다는 있는 그대로를 드러낸다. 그럼으로써 화장품 회사라는 카테고리에서는 전혀 생각지 못했던 '깜짝 스파크(unexpected spark)', 즉 재래시장 같은 풋풋한 활기와 재미를 제공한다.</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>화장품 업계에선 이단아지만 1995년에 설립된 이후 매년 평균 10%씩 성장해서 2013년에는 8000억 원의 매출을 달성했다.*</p>\n" +
                        "<p>* 2019년 기준 러쉬의 매출액은 1조 931억 원이다.</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>러쉬 영국 본사에서 창립자 마크 콘스탄틴(Mark Constantine)을 만났다. 그는 영국 남부에 있는 인구 15만 명의 항구 도시 풀(Poole)에 있었다. CEO 자리는 오래전에 물려주고 지금은 크리에이티브 디렉터로 일하고 있다.</p>\n" +
                        "<p><img src=\"https://user-images.githubusercontent.com/22764812/67147686-e5437200-f2d1-11e9-9609-1f43e0ece222.png\"/></p>\n" +
                        "<p>러쉬의 창립자 마크 콘스탄틴 &copy;러쉬</p>\n" +
                        "<blockquote>\n" +
                        "<p>일부러 향이 좋은 제품을 만들었는데, 포장재로 꽁꽁 싸매놓으면 고객이 냄새를 맡아보기 어렵잖아요. 저희는 이걸 '벌거벗었다'고 합니다. 우리는 모든 포장지 회사가 도산하기를 손꼽아 기다리고 있습니다. (웃음)</p>\n" +
                        "</blockquote>\n" +
                        "<p>그는 제품을 포장하지 않는 이유를 이렇게 설명했다. 러쉬 제품들은 향이 워낙 강렬해 매장이 백화점 7층에 있다면 5층에서부터 알아차릴 정도다.</p>"
        ));
//        addDisposable(repository.observeBookmarkById(bookmarkId)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(bookmark::setValue, error::setValue));
    }

    @NonNull
    public LiveData<Bookmark> getBookmark() {
        return bookmark;
    }
}
