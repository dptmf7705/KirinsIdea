package com.kirinsidea.ui.bookmarklist;

import android.text.Editable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.source.local.room.entity.FolderEntity;
import com.kirinsidea.ui.BaseViewModel;

public class BookmarkListViewModel extends BaseViewModel {
    private static final int PAGE_SIZE = 10;

    private LiveData<PagedList<BookmarkItem>> bookmarkList;

    public MutableLiveData<Integer> filterFolderId = new MutableLiveData<>();

    public LiveData<Integer> getFilterFolderId() {
        return filterFolderId;
    }

    private BookmarkRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull final BaseRepository... repositories) {
        this.repository = (BookmarkRepository) repositories[0];

        FolderEntity allBookmark = new FolderEntity("전체");
        loadBookmarkListSelected(allBookmark);
        return this;
    }

    public void loadBookmarkListSelected(FolderEntity item) {
        filterFolderId.setValue(item.getId());
        bookmarkList = Transformations.switchMap(filterFolderId, input -> {
            if(input == null || item.getName().equals("전체")){
                return new LivePagedListBuilder<>(repository.observeBookmarkList(), PAGE_SIZE).build();
            } else
                return new LivePagedListBuilder<>(repository.observeBookmarkListByFolderId(item), PAGE_SIZE).build();
        });
    }

    @NonNull
    public LiveData<PagedList<BookmarkItem>> getBookmarkList() {
        return bookmarkList;
    }
}
