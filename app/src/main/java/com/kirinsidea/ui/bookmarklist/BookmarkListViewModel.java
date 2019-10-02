package com.kirinsidea.ui.bookmarklist;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.source.local.room.entity.Bookmark;
import com.kirinsidea.ui.BaseViewModel;

public class BookmarkListViewModel extends BaseViewModel {
    private static final int PAGE_SIZE = 10;

    private LiveData<PagedList<Bookmark>> bookmarkList;

    @NonNull
    private final BookmarkRepository repository;

    private BookmarkListViewModel(@NonNull final BookmarkRepository repository) {
        this.repository = repository;
        loadBookmarkList();
    }

    private void loadBookmarkList() {
        bookmarkList = new LivePagedListBuilder<>(repository.observeBookmarkList(), PAGE_SIZE).build();
    }

    @NonNull
    public LiveData<PagedList<Bookmark>> getBookmarkList() {
        return bookmarkList;
    }


    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final BookmarkRepository repository;

        public Factory(@NonNull final BookmarkRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(BookmarkListViewModel.class)) {
                //noinspection unchecked
                return (T) new BookmarkListViewModel(repository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
        }
    }
}
