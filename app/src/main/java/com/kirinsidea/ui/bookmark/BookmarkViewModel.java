package com.kirinsidea.ui.bookmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.ui.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class BookmarkViewModel extends BaseViewModel {
    @NonNull
    private final MutableLiveData<Bookmark> bookmark = new MutableLiveData<>();
    @NonNull
    private final LiveData<String> content;

    @NonNull
    private final BookmarkRepository repository;

    private BookmarkViewModel(@NonNull BookmarkRepository repository) {
        this.repository = repository;
        this.content = Transformations.map(bookmark, bookmark -> {
//           TODO. bookmark.getPath() 해당 경로 파일 읽어서 본문 반환
            return "<h1>메인 스레드와 Handler</h1><p style=\"text-align: left; clear: none; float: none;\"><br /></p><h2>1. UI 작업을 메인 스레드에서만 하는 이유</h2><p>안드로이드에서의 UI 작업을 담당하는 메인 스레드(UI 스레드)는&nbsp;싱글 스레드 모델을 원칙으로한다. 뷰나 뷰그룹이 여러 스레드에 의해 업데이트될 수 있다면 교착 상태(Deadlock)나 경합상태(Race Condition) 등 여러문제가 발생할 수 있다.&nbsp;</p>\n" +
                    "<p><b><br /></b></p>\n" +
                    "<p><b>교착 상태(데드락, Deadlock)</b></p>\n" +
                    "<p>두 스레드 A와 B를 실행하는데&nbsp;리소스 a와 b가 모두 필요하다고 가정하자.&nbsp;</p>\n" +
                    "<p>- A는 현재 리소스 a를 사용하고 있고, 다음 작업에서 리소스 b가 필요하다.</p>\n" +
                    "<p>- B는 현재 리소스 b를 사용하고 있고, 다음 작업에서 리소스 a가 필요하다.</p>\n" +
                    "<p>위와 같은 상황이라면, A는 B가 종료될 때까지 기다리고 B는 A가 종료될 때까지 기다려야한다.</p>\n" +
                    "<p>즉, A와 B 모두 영원히 끝나지 않을 대기 상태에 빠지는 것이다. 이를 교착 상태(Deadlock)라고 한다.</p>";
        });
    }

    public void loadBookmark(final int bookmarkId) {
        addDisposable(repository.observeBookmarkById(bookmarkId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bookmark::setValue, error::setValue));
    }

    @NonNull
    public LiveData<Bookmark> getBookmark() {
        return bookmark;
    }

    @NonNull
    public LiveData<String> getContent() {
        return content;
    }

    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final BookmarkRepository repository;

        public Factory(@NonNull BookmarkRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(BookmarkViewModel.class)) {
                //noinspection unchecked
                return (T) new BookmarkViewModel(repository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
        }
    }
}
