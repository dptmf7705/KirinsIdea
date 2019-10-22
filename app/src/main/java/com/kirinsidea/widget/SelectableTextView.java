package com.kirinsidea.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class SelectableTextView extends AppCompatTextView implements ActionMode.Callback {
    @Nullable
    protected Pair<Integer, Integer> selection;  // 선택된 index
    @Nullable
    protected String selectedText;  // 선택된 text

    /**
     * selection 이 발생하면 데이터를 발행한다. (selection, selectedText 값이 바뀔 때)
     */
    @NonNull
    protected final PublishSubject<Boolean> selectionChangeStream = PublishSubject.create();

    public SelectableTextView(Context context) {
        super(context);
        initView();
    }

    public SelectableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SelectableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setTextIsSelectable(true);  // 텍스트 선택 가능하게
        setSelectAllOnFocus(false);  // 포커스 받으면 전체선택 되는거 막기
        setCustomSelectionActionModeCallback(this);  // 텍스트 선택시 시작되는 action 콜백
        setMovementMethod(LinkMovementMethod.getInstance());  // 특정 텍스트 클릭 이벤트 가능하게
    }

    /**
     * 텍스트가 선택되었을때 실행되는 콜백 메서드
     * <p>
     * start, end 값이 뒤집어질 경우를 대비하여 min, max 계산한다.
     * 계산 후 selection, selectedText 값 변경
     *
     * @param selStart 시작 인덱스
     * @param selEnd   끝 인덱스
     */
    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        final int startIndex = Math.min(selStart, selEnd);
        final int endIndex = Math.max(selStart, selEnd);
        if (startIndex < 0) {
            return;
        }
        setSelection(startIndex, endIndex);
        setSelectedText(startIndex, endIndex);
    }

    protected void setSelection(int startIndex, int endIndex) {
        setSelection(new Pair<>(startIndex, endIndex));
    }

    protected void setSelectedText(int startIndex, int endIndex) {
        setSelectedText(getText().subSequence(startIndex, endIndex).toString());
    }

    /**
     * selection 값이 변경되면 selectionChangeStream 발행
     */
    public void setSelection(@Nullable final Pair<Integer, Integer> selection) {
        this.selection = selection;
        this.selectionChangeStream.onNext(true);
    }

    /**
     * selectedText 값이 변경되면 selectionChangeStream 발행
     */
    public void setSelectedText(@Nullable String selectedText) {
        this.selectedText = selectedText;
        this.selectionChangeStream.onNext(true);
    }

    @Nullable
    public Pair<Integer, Integer> getSelection() {
        return selection;
    }

    @Nullable
    public String getSelectedText() {
        return selectedText;
    }

    public void startActionMode() {
        startActionMode(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        /* selection 바깥 터치 할 경우 action mode 종료 */
        setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mode.finish();
            }
            return false;
        });
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        menu.clear();
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }

    @Override
    protected void onDetachedFromWindow() {
        setCustomSelectionActionModeCallback(null);
        selectionChangeStream.onComplete();
        super.onDetachedFromWindow();
    }

    /**
     * debounce 로 selectionChangeStream 연속 발행 방지
     */
    @NonNull
    public Observable<Boolean> getSelectionChangeStream() {
        return selectionChangeStream
                .debounce(10, TimeUnit.MILLISECONDS)
                .serialize();
    }
}
