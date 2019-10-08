package com.kirinsidea.ui.bookmarklist;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.ui.BaseViewModel;

public class BookmarkListViewModel extends BaseViewModel {
    private static final int PAGE_SIZE = 10;

    private LiveData<PagedList<Bookmark>> bookmarkList;

    private BookmarkRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull final BaseRepository... repositories) {
        this.repository = (BookmarkRepository) repositories[0];
        loadBookmarkList();
        return this;
    }

    private void loadBookmarkList() {
        bookmarkList = new LivePagedListBuilder<>(repository.observeBookmarkList(), PAGE_SIZE).build();
    }

    @NonNull
    public LiveData<PagedList<Bookmark>> getBookmarkList() {
        return bookmarkList;
    }
}
