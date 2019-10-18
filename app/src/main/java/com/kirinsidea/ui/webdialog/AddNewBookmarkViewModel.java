package com.kirinsidea.ui.webdialog;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.repository.FolderRepository;
import com.kirinsidea.data.source.local.room.entity.Folder;
import com.kirinsidea.data.source.remote.kirin.request.NewBookmarkRequest;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.ui.BaseViewModel;
import com.kirinsidea.utils.DateUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class AddNewBookmarkViewModel extends BaseViewModel<WebNavigator> {
    private String TAG = "AddNewBookmarkViewModel";

    @NonNull
    private final MutableLiveData<String> folderName = new MutableLiveData<>();

    @NonNull
    private MutableLiveData<List<Folder>> folderList = new MutableLiveData<>();

    private MutableLiveData<String> status = new MutableLiveData<>();

    private BookmarkRepository bookmarkRepository;
    private FolderRepository folderRepository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull final BaseRepository... repositories) {
        this.bookmarkRepository = (BookmarkRepository) repositories[0];
        this.folderRepository = (FolderRepository) repositories[1];
        loadFolderList();
        return this;
    }

    private void loadFolderList() {
        addDisposable(folderRepository.observeFolderList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(folderList::setValue, error::setValue));
    }

    @NonNull
    public MutableLiveData<String> getFolderName() {
        return folderName;
    }

    @NonNull
    public LiveData<List<Folder>> getFolderList() {
        return folderList;
    }

    @NonNull
    public LiveData<String> getStatus() {
        return status;
    }

    public void addNewFolder() {
        final String newFolderName = folderName.getValue();
        final String storageTime = DateUtil.getCurrentDateTime();

        if (newFolderName == null || TextUtils.isEmpty(newFolderName)) {
            return;
        }
        addDisposable(folderRepository.observeAddNewFolder(new NewFolderRequest(newFolderName,
                storageTime))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> navigator.get().finishWebDialog(),
                        error::setValue));

        checkExistUrl(newFolderName);
    }

    public void checkExistUrl(String fname) {
        String url = navigator.get().getWebUrl();

        addDisposable(bookmarkRepository.checkIfExistUrl(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    if (integer == 0) {
                        addNewBookmark(url, fname);
                    } else {
                        status.setValue("ERROR");
                    }
                }));
    }

    public void addNewBookmark(String url, String fname) {
        addDisposable(bookmarkRepository.observeAddNewBookmark(new NewBookmarkRequest(url, fname))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> navigator.get().finishWebDialog(),
                        error::setValue));
    }
}
