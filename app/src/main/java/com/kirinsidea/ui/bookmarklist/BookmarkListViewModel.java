package com.kirinsidea.ui.bookmarklist;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.ui.BaseViewModel;

public class BookmarkListViewModel extends BaseViewModel {
    private static final int PAGE_SIZE = 10;

    private LiveData<PagedList<BookmarkItem>> bookmarkList;

    public MutableLiveData<Integer> filterFolderId = new MutableLiveData<>();

    private BookmarkRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull final BaseRepository... repositories) {
        this.repository = (BookmarkRepository) repositories[0];

        loadBookmarkListSelected(-1);

        bookmarkList = Transformations.switchMap(filterFolderId, folderId ->
                new LivePagedListBuilder<>(repository.observeBookmarkList(folderId), PAGE_SIZE).build());
        return this;
    }

    public void loadBookmarkListSelected(int id) {
        filterFolderId.setValue(id);
    }

    @NonNull
    public LiveData<PagedList<BookmarkItem>> getBookmarkList() {
        return bookmarkList;
    }

    public LiveData<Integer> getFilterFolderId() {
        return filterFolderId;
    }
}
