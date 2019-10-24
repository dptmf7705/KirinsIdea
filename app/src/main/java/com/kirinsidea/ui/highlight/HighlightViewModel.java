package com.kirinsidea.ui.highlight;

import android.text.TextUtils;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.HighlightRepository;
import com.kirinsidea.extension.livedata.LiveDataCompat;
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
    private final MutableLiveData<Pair<Integer, Integer>> selection = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<String> selectedText = new MutableLiveData<>();

    private LiveData<Highlight> selectedItem;

    private HighlightRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull BaseRepository... repositories) {
        this.repository = (HighlightRepository) repositories[0];
        this.selectedItem = Transformations.map(selectedText, selectedText -> {
            // text selection 끝났을 경우
            if (TextUtils.isEmpty(selectedText)) {
                return new Highlight();
            }

            Highlight highlight = selectedItem.getValue();
            if (highlight == null) {
                highlight = new Highlight();
            }
            highlight.setSelection(LiveDataCompat.getValue(this.selection, new Pair<>(0, 0)));
            highlight.setSelectedText(LiveDataCompat.getValue(this.selectedText, ""));
            return highlight;
        });
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
                .subscribe(highlight -> {
                    loadHighlightList();
                }, error::setValue));
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

        // selectedItem 변화를 알리기 위해
        LiveDataCompat.notifyDataChanged(this.selectedText);

        if (highlight.getId() == 0) {
            addNewHighlight(highlight);
        } else {
            updateHighlight(highlight);
        }
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
