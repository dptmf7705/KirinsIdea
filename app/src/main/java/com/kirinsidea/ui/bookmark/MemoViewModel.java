package com.kirinsidea.ui.bookmark;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.MemoRepository;
import com.kirinsidea.data.source.local.room.entity.Memo;
import com.kirinsidea.data.source.remote.kirin.request.NewMemoRequest;
import com.kirinsidea.ui.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MemoViewModel extends BaseViewModel {
    @NonNull
    private final MutableLiveData<Integer> bookmarkId = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<List<Memo>> memoList = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Pair<Integer, Integer>> selectedIndex = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> selectedText = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> inputMemo = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Memo> selectedItem = new MutableLiveData<>();

    private MemoRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull BaseRepository... repositories) {
        this.repository = (MemoRepository) repositories[0];
        return this;
    }

    void loadMemoList(final int bookmarkId) {
        this.bookmarkId.setValue(bookmarkId);
        loadMemoList();
    }

    private void loadMemoList() {
        final int bid = bookmarkId.getValue() == null ? 0 : this.bookmarkId.getValue();
        addDisposable(repository.observeMemoList(bid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(memoList::setValue, error::setValue));
    }

    public void addNewMemo() {
        addDisposable(repository
                .observeAddNewMemo(getNewMemoRequest())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> loadMemoList(), this.error::setValue));
    }

    public void deleteSelectedMemo() {
        final Memo memo = selectedItem.getValue();
        if (memo == null) {
            return;
        }
        addDisposable(repository
                .observeDeleteMemo(memo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    selectedItem.setValue(null);
                    loadMemoList();
                }, error::setValue));
    }

    private NewMemoRequest getNewMemoRequest() {
        return new NewMemoRequest(
                selectedIndex.getValue() == null ? 0 : selectedIndex.getValue().first,
                selectedIndex.getValue() == null ? 0 : selectedIndex.getValue().second,
                selectedText.getValue() == null ? "" : selectedText.getValue(),
                inputMemo.getValue() == null ? "" : inputMemo.getValue(),
                bookmarkId.getValue() == null ? 0 : bookmarkId.getValue());
    }

    @NonNull
    public LiveData<List<Memo>> getMemoList() {
        return memoList;
    }

    @NonNull
    public MutableLiveData<Memo> getSelectedItem() {
        return selectedItem;
    }

    @NonNull
    public MutableLiveData<Pair<Integer, Integer>> getSelectedIndex() {
        return selectedIndex;
    }

    @NonNull
    public MutableLiveData<String> getSelectedText() {
        return selectedText;
    }
}
