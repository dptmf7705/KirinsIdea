package com.kirinsidea.ui.bookmark;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.MemoRepository;
import com.kirinsidea.data.source.local.room.entity.Memo;
import com.kirinsidea.data.source.remote.kirin.request.NewMemoRequest;
import com.kirinsidea.ui.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MemoViewModel extends BaseViewModel {
    @NonNull
    private final MutableLiveData<String> content = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<List<Memo>> memoList = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Long> startIndex = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Long> endIndex = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> text = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> memo = new MutableLiveData<>();

    private LiveData<Integer> bookmarkId;

    private MemoRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull BaseRepository... repositories) {
        this.repository = (MemoRepository) repositories[0];
        return this;
    }

    public void loadMemoList(final int bookmarkId) {
        this.bookmarkId = Transformations.map(memoList, list ->
                list.isEmpty() ? bookmarkId : list.get(0).getBookmarkId());

        addDisposable(repository.observeMemoList(bookmarkId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(memoList::setValue, error::setValue));
    }

    public void addNewMemo() {
        addDisposable(repository
                .observeAddNewMemo(new NewMemoRequest(
                        startIndex.getValue() == null ? 0 : startIndex.getValue(),
                        endIndex.getValue() == null ? 0 : endIndex.getValue(),
                        text.getValue() == null ? "" : text.getValue(),
                        memo.getValue(),
                        bookmarkId.getValue() == null ? 0 : bookmarkId.getValue()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> { }, error::setValue));
    }
}
