package com.kirinsidea.widget;

import android.content.Context;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.App;
import com.kirinsidea.R;
import com.kirinsidea.ui.highlight.Highlight;
import com.kirinsidea.ui.memo.Memo;

public class HighlightTextView extends SpannableTextView {

    public HighlightTextView(Context context) {
        super(context);
    }

    public HighlightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HighlightTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Spannable 에 HighlightSpan, MemoSpan 을 추가한다.
     */
    @Override
    protected <T extends CharacterStyle> void addSpanToSpannable(@NonNull Spannable spannable,
                                                                 @NonNull T span) {
        if (span instanceof HighlightSpan) {
            HighlightSpan highlightSpan = (HighlightSpan) span;
            spannable.setSpan(span,
                    highlightSpan.getHighlight().getSelection().first,
                    highlightSpan.getHighlight().getSelection().second,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (span instanceof MemoSpan) {
            MemoSpan memoSpan = (MemoSpan) span;
            spannable.setSpan(span,
                    memoSpan.getMemo().getEndIndex(),
                    memoSpan.getMemo().getEndIndex() + 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }


    /**
     * MemoSpan
     * <p>
     * 메모 아이콘을 하이라이트 뒤에 추가하는 Span
     */
    public static class MemoSpan extends ImageSpan {
        @NonNull
        private final Memo memo;

        public MemoSpan(@NonNull final Memo memo) {
            super(App.instance(), R.drawable.ic_bp_small);
            this.memo = memo;
        }

        @NonNull
        public Memo getMemo() {
            return memo;
        }
    }


    /**
     * HighlightSpan
     * <p>
     * 하이라이트를 표시하는 Span
     */
    public static class HighlightSpan extends ClickableSpan {
        @Nullable
        private final SpanClickListener spanClickListener;
        @NonNull
        private final Highlight highlight;

        public HighlightSpan(@NonNull final Highlight highlight,
                             @Nullable final SpanClickListener spanClickListener) {
            this.highlight = highlight;
            this.spanClickListener = spanClickListener;
        }

        @Override
        public void onClick(@NonNull View widget) {
            if (spanClickListener != null) {
                spanClickListener.onClick();
            }
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.bgColor = App.instance().getResources().getColor(highlight.getHighlightColor().getColorResId());
            ds.setUnderlineText(false);
        }

        @NonNull
        public Highlight getHighlight() {
            return highlight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            HighlightSpan that = (HighlightSpan) o;
            return highlight.equals(that.highlight);
        }

        public interface SpanClickListener {
            void onClick();
        }
    }

}
