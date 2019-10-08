package com.kirinsidea.ui.webdialog;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.repository.FolderRepository;
import com.kirinsidea.data.source.remote.request.NewBookmarkRequest;
import com.kirinsidea.ui.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class AddNewBookmarkViewModel extends BaseViewModel<WebNavigator> {
    private String TAG = "AddNewBookmarkViewModel";

    @NonNull
    private final MutableLiveData<String> folderName = new MutableLiveData<>();

    private BookmarkRepository bookmarkRepository;
    private FolderRepository folderRepository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull final BaseRepository... repositories) {
        this.bookmarkRepository = (BookmarkRepository) repositories[0];
        this.folderRepository = (FolderRepository) repositories[1];
        return this;
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
        addDisposable(bookmarkRepository.observeAddNewBookmark(new NewBookmarkRequest(FirebaseAuth.getInstance().getUid(),
                url, fname))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(__ -> navigator.get().finishWebDialog(),
                        error::setValue));
    }
}
