package com.kirinsidea.widget;

import android.content.Context;
import android.text.Spannable;
import android.text.style.CharacterStyle;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.utils.ArrayUtil;

public abstract class SpannableTextView<T extends CharacterStyle> extends SelectableTextView {

    public SpannableTextView(Context context) {
        super(context);
    }

    public SpannableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpannableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * text 를 Spannable 형태로 반환
     */
    @Nullable
    public Spannable getSpannable() {
        return (Spannable) getText();
    }

    /**
     * Spannable 에 적용된 span 배열을 리턴한다.
     */
    @Nullable
    protected T[] getSpanArray(@NonNull final Spannable spannable,
                               @NonNull final Class<T> spanClass) {
        return spannable.getSpans(0, getText().length(), spanClass);
    }

    /**
     * span 배열을 spannable 에 추가한다.
     */
    public void addAllSpans(@NonNull final T[] spans) {
        Spannable spannable = getSpannable();
        if (spannable == null) {
            return;
        }
        for (T span : spans) {
            addSpanToSpannable(spannable, span);
        }
    }

    /**
     * Spannable 에 적용된 span 객체를 모두 삭제한다.
     */
    protected void removeAllSpans(@NonNull final Spannable spannable) {
        final T[] spanList = getSpanArray(spannable, getSpanClass());
        if (ArrayUtil.isEmpty(spanList)) {
            return;
        }

        for (T span : spanList) {
            spannable.removeSpan(span);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        Spannable spannable = getSpannable();
        if (spannable != null) {
            removeAllSpans(spannable);
        }
        super.onDetachedFromWindow();
    }

    protected abstract Class<T> getSpanClass();

    protected abstract void addSpanToSpannable(@NonNull final Spannable spannable,
                                               @NonNull final T span);
}