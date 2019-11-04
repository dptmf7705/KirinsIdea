package com.kirinsidea.widget;

import android.content.Context;
import android.text.Spannable;
import android.text.style.CharacterStyle;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.utils.ArrayUtil;

public abstract class SpannableTextView extends SelectableTextView {

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
     * Spannable 에 적용된 HighlightSpan 배열을 리턴한다.
     */
    @Nullable
    protected <T extends CharacterStyle> T[] getSpanArray(@NonNull final Spannable spannable,
                                                          @NonNull final Class<T> spanClass) {
        return spannable.getSpans(0, getText().length(), spanClass);
    }

    /**
     * span 을 spannable 에 추가한다.
     */
    public <T extends CharacterStyle> void addSpan(@NonNull final T span) {
        Spannable spannable = getSpannable();
        if (spannable == null) {
            return;
        }
        addSpanToSpannable(spannable, span);
    }

    public <T extends CharacterStyle> void removeAllSpans(@NonNull final Class<T> spanClass) {
        Spannable spannable = getSpannable();
        if (spannable == null) {
            return;
        }
        removeAllSpans(spannable, spanClass);
    }

    /**
     * Spannable 에 적용된 span 객체를 모두 삭제한다.
     */
    protected <T extends CharacterStyle> void removeAllSpans(@NonNull final Spannable spannable,
                                                             @NonNull final Class<T> spanClass) {
        final T[] spanList = getSpanArray(spannable, spanClass);
        if (ArrayUtil.isEmpty(spanList)) {
            return;
        }

        for (T span : spanList) {
            spannable.removeSpan(span);
        }
    }

    protected abstract <T extends CharacterStyle> void addSpanToSpannable(@NonNull Spannable spannable,
                                                                          @NonNull T span);
}
