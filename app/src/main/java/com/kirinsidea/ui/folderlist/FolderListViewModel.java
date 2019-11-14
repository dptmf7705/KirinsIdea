package com.kirinsidea.ui.folderlist;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.FolderRepository;
import com.kirinsidea.data.source.entity.FolderEntity;
import com.kirinsidea.data.source.remote.kirin.request.ChangeFolderRequest;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.extension.livedata.SingleLiveEvent;
import com.kirinsidea.ui.base.BaseViewModel;
import com.kirinsidea.utils.DateUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class FolderListViewModel extends BaseViewModel {
    @NonNull
    private final MutableLiveData<List<FolderEntity>> folderList = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<FolderEntity> selectedItem = new MutableLiveData<>();
    @NonNull
    private final SingleLiveEvent<Boolean> isClick = new SingleLiveEvent<>();
    @NonNull
    private final MutableLiveData<String> folderName = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Integer> folderId = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Boolean> isLongClick = new MutableLiveData<>();
    @NonNull
    private MutableLiveData<Boolean> isEdit = new MutableLiveData<>(); //폴더 이름 수정

    @NonNull
    private final MutableLiveData<List<FolderEntity>> longSelectedItem = new MutableLiveData<>(); //폴더 삭제 아이템들

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

    public void setLongClick(Boolean longClick) {
        this.isLongClick.setValue(longClick);
    }

    public void setEditMode(Boolean editClick) {
        this.isEdit.setValue(editClick);
    }

    // 롱클릭 후 선택된 폴더들 TODO 선택 취소시
    public void setIsLongClick(FolderEntity item){
        List<FolderEntity> items = null;
        //error!!
//        items.add(item);
        this.longSelectedItem.setValue(items);
        Log.d("EditTest","longSelectedItem: "+ longSelectedItem);
    }

    public void loadFolderList() {
        addDisposable(folderRepository.observeFolderList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(folderList::setValue, error::setValue));
    }

    public void clickFavorite(final FolderEntity item) {
        // true
        FolderEntity trueItem = new FolderEntity(item.getId(), item.getName(), item.getStoreTime(), true, false);
        // false
        FolderEntity firstItem = folderList.getValue().get(0);
        if (firstItem.isFavorite()) {
            FolderEntity falseItem = new FolderEntity(firstItem.getId(), firstItem.getName(), firstItem.getStoreTime(), false, false);
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

    /**
     * 폴더이름 수정
     * TODO 수정된 폴더이름 받기, 수정 폴더이름 room저장, 수정모드?
     */
    public void changeFolderName() {
        final String changeFolderName = folderName.getValue();
        final FolderEntity originalFolder = this.selectedItem.getValue();

        Log.d("EditTest","changeFolderName: "+ changeFolderName);

        addDisposable(folderRepository.observeChangeFolderName(new ChangeFolderRequest(
                originalFolder.getId(), changeFolderName), new FolderEntity(originalFolder.getId(),
                changeFolderName, originalFolder.getStoreTime(), originalFolder.isFavorite(), originalFolder.isSelected()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(folderId::setValue, error::setValue));
        toggleDrawer(false);
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

    @NonNull
    public LiveData<Boolean> getIsLongClick() {
        return isLongClick;
    }

    @NonNull
    public MutableLiveData<Boolean> getIsEdit() {
        return isEdit;
    }

    @NonNull
    public LiveData<List<FolderEntity>> getLongSelectedItem() {
        return longSelectedItem;
    }
}
