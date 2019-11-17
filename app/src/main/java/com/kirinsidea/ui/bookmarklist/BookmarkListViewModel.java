package com.kirinsidea.ui.bookmarklist;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.bookmark.BookmarkRepository;
import com.kirinsidea.data.repository.folder.FolderRepository;
import com.kirinsidea.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class BookmarkListViewModel extends BaseViewModel {
    private static final int PAGE_SIZE = 10;
    private static final String ALL_BOOKMARK = "-1";

    private LiveData<PagedList<BookmarkItem>> bookmarkList;

    public MutableLiveData<String> filterFolderId = new MutableLiveData<>();

    private BookmarkRepository bookmarkRepository;

    private FolderRepository folderRepository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull final BaseRepository... repositories) {
        this.bookmarkRepository = (BookmarkRepository) repositories[0];
        this.folderRepository = (FolderRepository) repositories[1];

//        SelectBookmarkList();
        loadBookmarkListSelected(ALL_BOOKMARK);

        bookmarkList = Transformations.switchMap(filterFolderId, folderId ->
                new LivePagedListBuilder<>(bookmarkRepository.observeBookmarkList(folderId), PAGE_SIZE).build());
        return this;
    }

    public void SelectBookmarkList() {
        if (folderRepository.observeBookmarkByFavorite() == null) {
            loadBookmarkListSelected(ALL_BOOKMARK);
        } else {
            addDisposable(folderRepository.observeBookmarkByFavorite()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::loadBookmarkListSelected));
        }
    }

    public void loadBookmarkListSelected(String id) {
        filterFolderId.setValue(id);
    }

    @NonNull
    public LiveData<PagedList<BookmarkItem>> getBookmarkList() {
        return bookmarkList;
    }

    public LiveData<String> getFilterFolderId() {
        return filterFolderId;
    }
}
