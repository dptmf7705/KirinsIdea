package com.kirinsidea.ui.webdialog;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.source.remote.request.WebClippingRequest;
import com.kirinsidea.ui.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class AddNewBookmarkViewModel extends BaseViewModel<WebNavigator> {
    private String TAG = "AddNewBookmarkViewModel";

    @NonNull
    private MutableLiveData<String> folderName = new MutableLiveData<>();

    @NonNull
    private final BookmarkRepository repository;

    public AddNewBookmarkViewModel(@NonNull BookmarkRepository repository) {
        this.repository = repository;
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
        addDisposable(repository.uploadWeb(new WebClippingRequest(FirebaseAuth.getInstance().getUid(),
                url, fname))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(__ -> navigator.get().finishWebDialog(),
                        error::setValue));
    }

    public static class Factory implements ViewModelProvider.Factory {

        @NonNull
        private final BookmarkRepository repository;

        public Factory(@NonNull BookmarkRepository repository) {
            this.repository = repository;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(AddNewBookmarkViewModel.class)) {
                //noinspection unchecked
                return (T) new AddNewBookmarkViewModel(repository);
            }
            throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
        }
    }

}
