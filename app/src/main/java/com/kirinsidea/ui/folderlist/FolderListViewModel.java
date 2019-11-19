package com.kirinsidea.ui.folderlist;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.App;
import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.folder.FolderRepository;
import com.kirinsidea.data.source.local.room.error.RoomException;
import com.kirinsidea.data.source.local.room.error.RoomResult;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitException;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitResultCode;
import com.kirinsidea.data.source.remote.kirin.request.ChangeFolderRequest;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.extension.livedata.SingleLiveEvent;
import com.kirinsidea.ui.base.BaseViewModel;
import com.kirinsidea.utils.DateUtil;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class FolderListViewModel extends BaseViewModel {
    @NonNull
    private final MutableLiveData<List<Folder>> folderList = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Folder> selectedItem = new MutableLiveData<>();
    @NonNull
    private final SingleLiveEvent<Boolean> isClick = new SingleLiveEvent<>();
    @NonNull
    private final MutableLiveData<String> folderName = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> folderId = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Boolean> isLongClick = new MutableLiveData<>();
    @NonNull
    private MutableLiveData<Boolean> isEdit = new MutableLiveData<>(); //폴더 이름 수정

    @NonNull
    private final MutableLiveData<List<Folder>> longSelectedItem = new MutableLiveData<>(); //폴더 삭제 아이템들

    private FolderRepository folderRepository;

    @NonNull
    private final SingleLiveEvent<String> message = new SingleLiveEvent<>();

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
    public void setIsLongClick(Folder item){
        List<Folder> items = null;
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

    public void clickFavorite(final Folder item) {
        // true
        Folder trueItem = new Folder(item.getId(), item.getName(), true, false);
        // false
        Folder firstItem = folderList.getValue().get(0);
        if (firstItem.isFavorite()) {
            Folder falseItem = new Folder(firstItem.getId(), firstItem.getName(), false, false);
            addDisposable(folderRepository.observeChangeFavorite(trueItem)
                    .observeOn(AndroidSchedulers.mainThread())
                    .mergeWith(folderRepository.observeChangeFavorite(falseItem))
                    .subscribe(this::loadFolderList));
        } else {
            addDisposable(folderRepository.observeChangeFavorite(trueItem)
                    .observeOn(AndroidSchedulers.mainThread())
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
                .doOnError(error ->{
                    if(error instanceof RetrofitException){ // 서버 통신 에러
                        RetrofitResultCode code = ((RetrofitException) error).getRetrofitResultCode();
                        if(code == RetrofitResultCode.FOLDER_ERROR_501){ // 예기치 못한 에러발생
                            this.message.setValue(code.getMessage());
                        }
                    } else if(error instanceof RoomException){ // 룸 에러
                        RoomResult result = ((RoomException) error).getRoomResult();
                        if(result == RoomResult.FOLDER_ALREADY_EXIST){ // 이미 존재
                            Toast.makeText(App.instance().getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .subscribe(id -> {
                    folderId.setValue(id);
                    folderName.setValue(null);
                }));
    }

    /**
     * 폴더이름 수정
     * TODO 수정된 폴더이름 받기, 수정 폴더이름 room저장, 수정모드?
     */
    public void changeFolderName() {
        final String changeFolderName = folderName.getValue();
        final Folder originalFolder = this.selectedItem.getValue();

        Log.d("EditTest","changeFolderName: "+ changeFolderName);

        addDisposable(folderRepository.observeChangeFolderName(new ChangeFolderRequest(
                originalFolder.getId(), changeFolderName), new Folder(originalFolder.getId(),
                changeFolderName,originalFolder.isFavorite(), originalFolder.isSelected()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(folderId::setValue, error::setValue));
        toggleDrawer(false);
    }

    @NonNull
    public LiveData<List<Folder>> getFolderList() {
        return folderList;
    }

    @NonNull
    public LiveData<Folder> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Folder item) {
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
    public LiveData<String> getFolderId() {
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
    public LiveData<List<Folder>> getLongSelectedItem() {
        return longSelectedItem;
    }
}
