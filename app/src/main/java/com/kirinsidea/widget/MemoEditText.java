package com.kirinsidea.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.Scroller;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

@SuppressLint({"ClickableViewAccessibility"})
public class MemoEditText extends AppCompatEditText {
    @NonNull
    private final PublishSubject<Boolean> enabledChangeStream = PublishSubject.create();

    public MemoEditText(Context context) {
        super(context);
        initView(context);
    }

    public MemoEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MemoEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE |
                InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE |
                InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        setSingleLine(false);
        setBackground(null);
        setTextAlignment(TEXT_ALIGNMENT_TEXT_START);
        setOverScrollMode(OVER_SCROLL_NEVER);
//        setScroller(new Scroller(context));
//        setVerticalScrollBarEnabled(true);
//        setMovementMethod(new ScrollingMovementMethod());
        if (isEnabled()) {
            post(this::openKeyboard);
        } else {
            post(this::closeKeyboard);
        }
    }

    float oldX;
    float oldY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldX = event.getX();
                oldY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(event.getX() - oldX) < 20 &&
                        Math.abs(event.getY() - oldY) < 20) {

                    if (!isEnabled()) {
                        setEnabled(Boolean.TRUE);
                    }
                    Layout layout = getLayout();
                    float x = event.getX() + getScrollX();
                    float y = event.getY() + getScrollY();
                    int line = layout.getLineForVertical((int) y);
                    int offset = layout.getOffsetForHorizontal(line, x);
                    if (offset > 0) {
                        if (x > layout.getLineMax(line)) {
                            setSelection(offset);
                        } else {
                            setSelection(offset - 1);
                        }
                    }
                }
                break;
        }
        return true;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            post(this::openKeyboard);
        } else {
            post(this::closeKeyboard);
        }
        setCursorVisible(enabled);
        enabledChangeStream.onNext(enabled ? Boolean.TRUE : Boolean.FALSE);
    }

    @NonNull
    public Observable<Boolean> getEnabledChangeStream() {
        return enabledChangeStream.distinctUntilChanged().serialize();
    }

    public void openKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT);
    }

    public void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindowToken(), InputMethodManager.SHOW_IMPLICIT);
    }
}
