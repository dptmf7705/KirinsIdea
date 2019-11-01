package com.kirinsidea.ui.folderlist;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.FolderRepository;
import com.kirinsidea.data.source.local.room.entity.FolderEntity;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.extension.livedata.SingleLiveEvent;
import com.kirinsidea.ui.BaseViewModel;
import com.kirinsidea.utils.DateUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class FolderListViewModel extends BaseViewModel {
    @NonNull
    private MutableLiveData<List<FolderEntity>> folderList = new MutableLiveData<>();
    @NonNull
    private MutableLiveData<FolderEntity> selectedItem = new MutableLiveData<>();
    @NonNull
    private SingleLiveEvent<Boolean> isClick = new SingleLiveEvent<>();
    @NonNull
    private final MutableLiveData<String> folderName = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Integer> folderId = new MutableLiveData<>();

    private FolderRepository folderRepository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull BaseRepository... repositories) {
        this.folderRepository = (FolderRepository) repositories[0];
        loadFolderList();
        return this;
    }

    public void toggleDrawer(Boolean click) {
        isClick.setValue(click);
    }

    public void loadFolderList() {
        addDisposable(folderRepository.observeFolderList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(folderList::setValue, error::setValue));
    }

    public void clickFavorite(final FolderEntity item){
        // true
        FolderEntity trueItem = new FolderEntity(item.getId(), item.getName(), item.getStoreTime(), true);
        // false
        FolderEntity firstItem = folderList.getValue().get(0);
        if(firstItem.isFavorite()){
            FolderEntity falseItem = new FolderEntity(firstItem.getId(), firstItem.getName(), firstItem.getStoreTime(), false);
            addDisposable(folderRepository.observeChangeFavorite(trueItem)
                    .mergeWith(folderRepository.observeChangeFavorite(falseItem))
                    .subscribe(this::loadFolderList));
        } else {
            addDisposable(folderRepository.observeChangeFavorite(trueItem)
                    .subscribe(this::loadFolderList));
        }
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
                .subscribe(id -> {
                    folderId.setValue(id);
                    folderName.setValue(null);
                }, error::setValue));
    }

    @NonNull
    public LiveData<List<FolderEntity>> getFolderList() {
        return folderList;
    }

    @NonNull
    public LiveData<FolderEntity> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(FolderEntity item) {
        selectedItem.setValue(item);
    }

    @NonNull
    public LiveData<Boolean> getIsClick() {
        return isClick;
    }

    @NonNull
    public MutableLiveData<String> getFolderName() {
        return folderName;
    }

    @NonNull
    public LiveData<Integer> getFolderId() {
        return folderId;
    }

}
