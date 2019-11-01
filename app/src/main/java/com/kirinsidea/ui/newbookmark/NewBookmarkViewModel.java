package com.kirinsidea.ui.newbookmark;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.ui.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class NewBookmarkViewModel extends BaseViewModel<WebNavigator> {

    private MutableLiveData<String> status = new MutableLiveData<>();

    private BookmarkRepository bookmarkRepository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull final BaseRepository... repositories) {
        this.bookmarkRepository = (BookmarkRepository) repositories[0];
        return this;
    }

    @NonNull
    public LiveData<String> getStatus() {
        return status;
    }

    public void checkExistUrl(int fId) {
        String url = navigator.get().getWebUrl();

        addDisposable(bookmarkRepository.checkIfExistUrl(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    if (integer == 0) {
                        addNewBookmark(url, fId);
                        Log.d("##FolderTest","checkExistUrl: " + url + " fId: "+fId);
                    } else {
                        status.setValue("ERROR");
                    }
                }));
    }

    public void addNewBookmark(String url, int fId) {
        addDisposable(bookmarkRepository.observeAddNewBookmark(new NewBookmarkRequest(url, fId))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> navigator.get().finishWebDialog(),
                        error::setValue));
    }
}
