package com.kirinsidea.ui.highlight;

import android.text.TextUtils;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.highlight.HighlightRepository;
import com.kirinsidea.extension.livedata.LiveDataCompat;
import com.kirinsidea.extension.livedata.SingleLiveEvent;
import com.kirinsidea.ui.base.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class HighlightViewModel extends BaseViewModel {
    @NonNull
    private final SingleLiveEvent<String> bookmarkId = new SingleLiveEvent<>();
    @NonNull
    private final MutableLiveData<List<Highlight>> highlightList = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Pair<Integer, Integer>> selection = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> selectedText = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Highlight> selectedItem = new MutableLiveData<>();

    private LiveData<Boolean> isSelectionMode; // 바텀시트 열림, 닫힘 바인딩

    private HighlightRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull BaseRepository... repositories) {
        this.repository = (HighlightRepository) repositories[0];
        this.isSelectionMode = Transformations.map(selectedText, selectedText -> {
            final boolean isEmpty = TextUtils.isEmpty(selectedText);
            if (isEmpty && selectedItem.getValue() != null) {
                selectedItem.setValue(null);
            }
            return !isEmpty;
        });
        return this;
    }

    /**
     * 하이라이트 리스트 로딩 - 뷰에서 호출
     */
    public void loadHighlightList(final String bookmarkId) {
        if (this.bookmarkId.getValue() == null) {
            this.bookmarkId.setValue(bookmarkId);
        }
        loadHighlightList();
    }

    /**
     * 하이라이트 리스트 로딩
     */
    private void loadHighlightList() {
        final String id = this.bookmarkId.getValue();
        if (id == null) {
            return;
        }
        addDisposable(repository.observeHighlightListByBookmarkId(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(highlightList::setValue, error::setValue));
    }

    /**
     * 새 하이라이트 추가
     */
    private void addNewHighlight(@NonNull final Highlight newHighlight) {
        addDisposable(repository.observeAddNewHighlight(newHighlight)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(highlight -> {
                    this.selectedItem.setValue(highlight);
                    loadHighlightList();
                }, error::setValue));
    }

    /**
     * 하이라이트 수정
     */
    private void updateHighlight(@NonNull final Highlight highlight) {
        addDisposable(repository.observeUpdateHighlight(highlight)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    this.selectedItem.setValue(item);
                    loadHighlightList();
                }, error::setValue));
    }

    /**
     * 하이라이트 삭제 - 뷰에서 호출
     */
    public void deleteHighlight() {
        final Highlight highlight = this.selectedItem.getValue();
        if (highlight == null) {
            return;
        }
        deleteHighlight(highlight);
    }

    /**
     * 하이라이트 삭제
     */
    private void deleteHighlight(@NonNull final Highlight highlight) {
        addDisposable(repository.observeDeleteHighlight(highlight)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    resetSelectedItem();
                    loadHighlightList();
                }, error::setValue));
    }

    /**
     * 현재 선택된 하이라이트 변경
     */
    public void setSelectedItem(@NonNull final Highlight item) {
        this.selectedText.setValue(item.getHighlightText());
        this.selection.setValue(item.getSelection());
        this.selectedItem.setValue(item);
    }

    /**
     * 현재 선택된 하이라이트 선택 해제
     */
    private void resetSelectedItem() {
        this.selectedText.setValue(null);
    }

    /**
     * 하이라이트 색상 선택 - 뷰에서 호출 (추가 또는 수정)
     */
    public void setHighlightColor(@NonNull final HighlightColor color) {
        setHighlightProperty(
                LiveDataCompat.getValue(this.selection, new Pair<>(0, 0)),
                LiveDataCompat.getValue(this.selectedText, ""),
                color);
    }

    /**
     * 새 하이라이트 초기화 또는 속성(selection, selectedText, color) 변경
     */
    private void setHighlightProperty(@NonNull final Pair<Integer, Integer> selection,
                                      @NonNull final String selectedText,
                                      @NonNull final HighlightColor color) {
        Highlight highlight = this.selectedItem.getValue();
        if (highlight == null) {
            addNewHighlight(createNewHighlight(selection, selectedText, color));
        } else if (highlight.getHighlightColor() != color) {
            highlight.setSelection(selection);
            highlight.setHighlightText(selectedText);
            highlight.setHighlightColor(color);
            updateHighlight(highlight);
        }
    }

    /**
     * 새 하이라이트 객체 생성
     */
    private Highlight createNewHighlight(@NonNull final Pair<Integer, Integer> selection,
                                         @NonNull final String selectedText,
                                         @NonNull final HighlightColor color) {
        final String bookmarkId = this.bookmarkId.getValue();
        if (bookmarkId == null) {
            throw new IllegalStateException("bookmarkId cannot be NULL");
        }
        return new Highlight(bookmarkId, selection, selectedText, color);
    }

    @NonNull
    public LiveData<Boolean> getIsSelectionMode() {
        return isSelectionMode;
    }

    @NonNull
    public LiveData<Highlight> getSelectedItem() {
        return selectedItem;
    }

    @NonNull
    public MutableLiveData<Pair<Integer, Integer>> getSelection() {
        return selection;
    }

    @NonNull
    public MutableLiveData<String> getSelectedText() {
        return selectedText;
    }

    @NonNull
    public LiveData<List<Highlight>> getHighlightList() {
        return highlightList;
    }
}
