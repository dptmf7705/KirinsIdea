package com.kirinsidea.ui.bookmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.ui.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

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
        addDisposable(repository.observeBookmarkById(bookmarkId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(bookmark::setValue, error::setValue));
    }

    @NonNull
    public LiveData<Bookmark> getBookmark() {
        return bookmark;
    }
}
