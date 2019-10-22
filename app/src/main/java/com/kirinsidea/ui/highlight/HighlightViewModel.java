package com.kirinsidea.ui.highlight;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.HighlightRepository;
import com.kirinsidea.extension.livedata.SingleLiveEvent;
import com.kirinsidea.ui.BaseViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class HighlightViewModel extends BaseViewModel {
    @NonNull
    private final SingleLiveEvent<Integer> bookmarkId = new SingleLiveEvent<>();
    @NonNull
    private final MutableLiveData<List<Highlight>> highlightList = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Highlight> selectedItem = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();

    private HighlightRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull BaseRepository... repositories) {
        this.repository = (HighlightRepository) repositories[0];
        this.selectedItem.setValue(new Highlight());
        return this;
    }

    /**
     * 하이라이트 리스트 로딩 - 뷰에서 호출
     */
    public void loadHighlightList(final int bookmarkId) {
        if (this.bookmarkId.getValue() == null) {
            this.bookmarkId.setValue(bookmarkId);
        }
        loadHighlightList();
    }

    /**
     * 하이라이트 리스트 로딩
     */
    private void loadHighlightList() {
        final Integer id = this.bookmarkId.getValue();
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
                .subscribe(this::loadHighlightList, error::setValue));
    }

    /**
     * 하이라이트 수정
     */
    private void updateHighlight(@NonNull final Highlight highlight) {
        addDisposable(repository.observeUpdateHighlight(highlight)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadHighlightList, error::setValue));
    }

    /**
     * 하이라이트 삭제
     */
    public void deleteHighlight() {
        final Highlight highlight = this.selectedItem.getValue();
        if (highlight == null) {
            return;
        }
        addDisposable(repository.observeDeleteHighlight(highlight)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::loadHighlightList, error::setValue));
    }

    /**
     * 하이라이트 색상 선택
     */
    public void setHighlightColor(@NonNull final HighlightColor color) {
        final Highlight highlight = this.selectedItem.getValue();
        if (highlight == null) {
            return;
        }
        highlight.setColor(color);

        if (Boolean.TRUE.equals(this.isUpdating.getValue())) {
            updateHighlight(highlight);
        } else {
            addNewHighlight(highlight);
        }
    }

    @NonNull
    public MutableLiveData<Highlight> getSelectedItem() {
        return selectedItem;
    }

    @NonNull
    public LiveData<List<Highlight>> getHighlightList() {
        return highlightList;
    }

    @NonNull
    public MutableLiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }
}
