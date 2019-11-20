package com.kirinsidea.ui.newbookmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.bookmark.BookmarkRepository;
import com.kirinsidea.data.source.local.room.error.RoomException;
import com.kirinsidea.data.source.local.room.error.RoomResult;
import com.kirinsidea.data.source.remote.kirin.error.FileDownloadException;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitException;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitResultCode;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.extension.livedata.SingleLiveEvent;
import com.kirinsidea.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class NewBookmarkViewModel extends BaseViewModel<WebNavigator> {

    private BookmarkRepository bookmarkRepository;

    @NonNull
    private final SingleLiveEvent<String> message = new SingleLiveEvent<>();

    @NonNull
    @Override
    public BaseViewModel init(@NonNull final BaseRepository... repositories) {
        this.bookmarkRepository = (BookmarkRepository) repositories[0];
        return this;
    }

    @NonNull
    public LiveData<String> getMessage() {
        return message;
    }

    /**
     * 북마크 추가시 url 중복 검사
     */
    public void checkExistUrl(String fId) {
        String url = navigator.get().getWebUrl();

        addDisposable(bookmarkRepository.checkIfExistUrl(url)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> {
                    if (error instanceof RoomException) { // 룸 에러
                        RoomResult result = ((RoomException) error).getRoomResult();
                        if (result == RoomResult.BOOKMARK_ALREADY_EXIST) { // 이미 존재
                            this.message.setValue(result.getMessage());
                        }
                    }
                }).subscribe(integer -> {
                    if(integer == 0)
                        addNewBookmark(url, fId);
                }));
    }

    public void addNewBookmark(String url, String fId) {
        addDisposable(bookmarkRepository.observeAddNewBookmark(new NewBookmarkRequest(url, fId))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> {
                    if (error instanceof RetrofitException) { // 서버 통신 에러
                        RetrofitResultCode code = ((RetrofitException) error).getRetrofitResultCode();
                        if (code == RetrofitResultCode.BOOKMARK_ERROR_406) { // 예기치 못한 에러발생
                            this.message.setValue(code.getMessage());
                        }
                    } else if (error instanceof FileDownloadException) { // html 파일 다운로드 에러
                        RetrofitResultCode code = ((FileDownloadException) error).getRetrofitResultCode();
                        if (code == RetrofitResultCode.BOOKMARK_ERROR_407) { // 파일 다운로드 에러
                            this.message.setValue(code.getMessage());
                        }
                    }
                })
                .subscribe(() -> navigator.get().finishWebDialog()));
    }
}
