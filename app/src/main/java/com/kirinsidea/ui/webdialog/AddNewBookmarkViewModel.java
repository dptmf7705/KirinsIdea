package com.kirinsidea.ui.webdialog;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.repository.FolderRepository;
import com.kirinsidea.data.source.remote.request.AddNewBookmarkRequest;
import com.kirinsidea.ui.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class AddNewBookmarkViewModel extends BaseViewModel<WebNavigator> {
    private String TAG = "AddNewBookmarkViewModel";

    @NonNull
    private MutableLiveData<String> folderName = new MutableLiveData<>();

    @NonNull
    private final BookmarkRepository bookmarkRepository;

    @NonNull
    private final FolderRepository folderRepository;

    public AddNewBookmarkViewModel(@NonNull BookmarkRepository bookmarkRepository, @NonNull FolderRepository folderRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.folderRepository = folderRepository;
    }

    @NonNull
    public MutableLiveData<String> getFolderName() {
        return folderName;
    }

    public void startGetWebUrl() {
        String url = navigator.get().getWebUrl();
        final String fname = folderName.getValue();
        if (fname == null || TextUtils.isEmpty(fname)) {
            return;
        }
        addDisposable(bookmarkRepository.observeAddNewBookmark(new AddNewBookmarkRequest(FirebaseAuth.getInstance().getUid(),
                url, fname))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(__ -> navigator.get().finishWebDialog(),
                        error::setValue));
    }

    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final BookmarkRepository bookmarkRepository;

        @NonNull
        private final FolderRepository folderRepository;

        public Factory(@NonNull BookmarkRepository bookmarkRepository, @NonNull FolderRepository folderRepository) {
            this.bookmarkRepository = bookmarkRepository;
            this.folderRepository = folderRepository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(AddNewBookmarkViewModel.class)) {
                //noinspection unchecked
                return (T) new AddNewBookmarkViewModel(bookmarkRepository, folderRepository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
        }
    }

}
