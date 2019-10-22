package com.kirinsidea.widget;

import android.content.Context;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.ui.highlight.Highlight;

public class HighlightTextView extends SpannableTextView<HighlightTextView.HighlightSpan> {

    public HighlightTextView(Context context) {
        super(context);
    }

    public HighlightTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HighlightTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void addSpanToSpannable(@NonNull final Spannable spannable,
                                      @NonNull final HighlightSpan span) {
        spannable.setSpan(span,
                span.getHighlight().getSelection().first,
                span.getHighlight().getSelection().second,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    @Override
    protected Class<HighlightSpan> getSpanClass() {
        return HighlightSpan.class;
    }


    public static class HighlightSpan extends ClickableSpan {
        @Nullable
        private SpanClickListener spanClickListener;

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
            ds.bgColor = highlight.getColor().getColorResId();
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
