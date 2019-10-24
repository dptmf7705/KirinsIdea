package com.kirinsidea.extension.databinding;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.kirinsidea.ui.highlight.Highlight;
import com.kirinsidea.utils.CollectionUtil;
import com.kirinsidea.widget.HighlightTextView;
import com.kirinsidea.widget.HtmlTextView;
import com.kirinsidea.widget.SelectableTextView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class TextViewAdapters {

    @BindingAdapter({"html"})
    public static void setTextFromHtml(@NonNull final HtmlTextView textView,
                                       @Nullable final CharSequence html) {
        if (html == null || TextUtils.isEmpty(html)) {
            return;
        }

        textView.setTextFromHtml(html);
    }

    /**
     * 하이라이트 span 추가
     */
    @BindingAdapter({"highlightItems"})
    public static void setHighlightItems(@NonNull final HighlightTextView textView,
                                         @Nullable final List<Highlight> items) {
        textView.removeAllSpans();

        if (CollectionUtil.isEmpty(items)) {
            return;
        }

        // 그리기
        for (Highlight item : items) {
            textView.addSpan(new HighlightTextView.HighlightSpan(item, () -> {
                textView.setSelection(item.getSelection());
                textView.setSelectedText(item.getSelectedText());
            }));
        }
    }


    /**
     * SelectableTextView selection tow-way binding
     */
    @BindingAdapter({"selection"})
    public static void setSelection(@NonNull final SelectableTextView textView,
                                    @NonNull final Pair<Integer, Integer> selection) {
        textView.setSelection(selection);
    }

    @InverseBindingAdapter(attribute = "selection", event = "selectionChanged")
    public static Pair<Integer, Integer> getSelection(@NonNull final SelectableTextView textView) {
        return textView.getSelection();
    }

    @SuppressLint({"ClickableViewAccessibility", "CheckResult"})
    @BindingAdapter("selectionChanged")
    public static void setSelectionListener(@NonNull final SelectableTextView textView,
                                            @Nullable final InverseBindingListener listener) {
        textView.getSelectionChangeStream()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(__ -> {
                    if (listener != null) {
                        listener.onChange();
                    }
                }, Throwable::printStackTrace);
    }

    /**
     * SelectableTextView selectedText tow-way binding
     */
    @BindingAdapter({"selectedText"})
    public static void setSelectedText(@NonNull final SelectableTextView textView,
                                       @NonNull final String selectedText) {
        textView.setSelectedText(selectedText);
    }

    @InverseBindingAdapter(attribute = "selectedText", event = "selectedTextChanged")
    public static String getSelectedText(@NonNull final SelectableTextView textView) {
        return textView.getSelectedText();
    }

    @SuppressLint({"ClickableViewAccessibility", "CheckResult"})
    @BindingAdapter("selectedTextChanged")
    public static void setTextListener(@NonNull final SelectableTextView textView,
                                       @Nullable final InverseBindingListener listener) {
        textView.getSelectedTextChangeStream()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(__ -> {
                    if (listener != null) {
                        listener.onChange();
                    }
                }, Throwable::printStackTrace);
    }

}
