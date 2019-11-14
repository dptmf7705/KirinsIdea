package com.kirinsidea.ui.memo;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.MemoRepository;
import com.kirinsidea.extension.livedata.LiveDataCompat;
import com.kirinsidea.extension.livedata.SingleLiveEvent;
import com.kirinsidea.ui.base.BaseViewModel;
import com.kirinsidea.ui.highlight.Highlight;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MemoViewModel extends BaseViewModel {
    @NonNull
    private final SingleLiveEvent<Integer> bookmarkId = new SingleLiveEvent<>();
    @NonNull
    private final MutableLiveData<List<Memo>> memoList = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Highlight> highlight = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> memoContent = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Memo> selectedItem = new MutableLiveData<>();

    @NonNull
    private final MutableLiveData<Boolean> isMemoOpen = new MutableLiveData<>();  // 바텀시트 열기, 닫기 바인딩
    @NonNull
    private final MutableLiveData<Boolean> isEditMode = new MutableLiveData<>();  // 메모 읽기모드, 수정모드 바인딩

    private MemoRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull BaseRepository... repositories) {
        this.repository = (MemoRepository) repositories[0];
        return this;
    }

    /**
     * 메모 로딩 - 뷰에서 호출
     */
    public void loadMemoList(final int bookmarkId) {
        if (this.bookmarkId.getValue() == null) {
            this.bookmarkId.setValue(bookmarkId);
        }
        loadMemoList();
    }

    /**
     * 메모 로딩
     */
    private void loadMemoList() {
        final Integer bookmarkId = this.bookmarkId.getValue();
        if (bookmarkId == null) {
            return;
        }
        addDisposable(repository.observeMemoListByBookmarkId(bookmarkId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(memoList::setValue, error::setValue));
    }

    /**
     * 메모 추가 또는 수정 - 뷰에서 호출
     */
    public void finishEditMemo() {
        final Memo memo = this.selectedItem.getValue();
        final String memoContent = LiveDataCompat.getValue(this.memoContent, "");
        if (memo == null) {
            if (!TextUtils.isEmpty(memoContent)) {
                addNewMemo(createNewMemo(memoContent));  // 새 메모 추가
            }
            return;
        }
        if (TextUtils.isEmpty(memoContent)) {
            deleteMemo(memo);  // 메모 삭제
            return;
        }
        if (!memoContent.equals(memo.getMemoContent())) {
            memo.setMemoContent(memoContent);
            updateMemo(memo);  // 메모 수정
        }
    }

    /**
     * 새 메모 추가
     */
    private void addNewMemo(@NonNull final NewMemo newMemo) {
        addDisposable(repository.observeAddNewMemo(newMemo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    loadMemoList();
                    setSelectedItem(item);
                }, error::setValue));
    }

    /**
     * 메모 수정
     */
    private void updateMemo(@NonNull final Memo memo) {
        addDisposable(repository.observeUpdateMemo(memo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    loadMemoList();
                    setSelectedItem(item);
                }, error::setValue));
    }

    /**
     * 메모 삭제
     */
    private void deleteMemo(@NonNull final Memo memo) {
        addDisposable(repository.observeDeleteMemo(memo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    loadMemoList();
                    setSelectedItem(null);
                }, error::setValue));
    }

    /**
     * 새 메모 객체 생성
     */
    @NonNull
    private NewMemo createNewMemo(@NonNull final String memoContent) {
        final Integer bookmarkId = this.bookmarkId.getValue();
        final Highlight highlight = this.highlight.getValue();
        if (bookmarkId == null || highlight == null) {
            throw new IllegalStateException("bookmarkId or highlight cannot be NULL");
        }
        return new NewMemo(bookmarkId, highlight.getHighlightId(), memoContent);
    }

    /**
     * 현재 선택된 하이라이트
     */
    public void setHighlight(@Nullable final Highlight highlight) {
        this.highlight.setValue(highlight);
        setSelectedItem(highlight == null ?
                null : findMemoByHighlightId(highlight.getHighlightId()));
    }

    /**
     * 하이라이트에 기존 메모가 있는지 찾기
     */
    @Nullable
    private Memo findMemoByHighlightId(final int highlightId) {
        for (Memo memo : LiveDataCompat.getListValue(this.memoList)) {
            // 기존 메모 있음
            if (memo.getHighlightId() == highlightId) {
                return memo;
            }
        }
        // 기존 메모 없음
        return null;
    }

    /**
     * 현재 선택된 메모 변경
     */
    private void setSelectedItem(@Nullable final Memo item) {
        this.selectedItem.setValue(item);
        this.memoContent.setValue(item == null ? null : item.getMemoContent());
    }

    /**
     * 메모 확인 / 수정 모드 변경
     */
    public void setIsEditMode(final boolean isEditMode) {
        this.isEditMode.setValue(isEditMode);
    }

    /**
     * 메모 창 열기 / 닫기
     */
    public void setIsMemoOpen(final boolean isMemoOpen) {
        this.isMemoOpen.setValue(isMemoOpen);
        this.isEditMode.setValue(selectedItem.getValue() == null);
    }

    @NonNull
    public LiveData<Highlight> getHighlight() {
        return highlight;
    }

    @NonNull
    public MutableLiveData<Boolean> getIsMemoOpen() {
        return isMemoOpen;
    }

    @NonNull
    public LiveData<List<Memo>> getMemoList() {
        return memoList;
    }

    @NonNull
    public MutableLiveData<String> getMemoContent() {
        return memoContent;
    }

    @NonNull
    public MutableLiveData<Boolean> getIsEditMode() {
        return isEditMode;
    }

    @NonNull
    public LiveData<Memo> getSelectedItem() {
        return selectedItem;
    }
}
