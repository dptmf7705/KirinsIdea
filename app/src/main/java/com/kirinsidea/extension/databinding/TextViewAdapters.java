package com.kirinsidea.extension.databinding;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.kirinsidea.ui.highlight.Highlight;
import com.kirinsidea.ui.highlight.HighlightViewModel;
import com.kirinsidea.ui.login.LinkText;
import com.kirinsidea.ui.memo.Memo;
import com.kirinsidea.utils.CollectionUtil;
import com.kirinsidea.widget.HighlightTextView;
import com.kirinsidea.widget.HtmlTextView;
import com.kirinsidea.widget.MemoEditText;
import com.kirinsidea.widget.SelectableTextView;

import java.util.List;
import java.util.regex.Pattern;

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

    @BindingAdapter({"linkItems"})
    public static void setTextFromHtml(@NonNull final AppCompatTextView textView,
                                       @Nullable final LinkText[] linkItems) {
        if (linkItems == null){
            return;
        }

        for (LinkText item : linkItems) {
            Linkify.addLinks(textView,
                    Pattern.compile(item.getString()),
                    item.getUrl(),
                    null,
                    (match, url) -> "");
        }
    }

    /**
     * 하이라이트 span 추가
     */
    @BindingAdapter({"highlightItems", "vm"})
    public static void setHighlightItems(@NonNull final HighlightTextView textView,
                                         @Nullable final List<Highlight> highlightList,
                                         @NonNull final HighlightViewModel vm) {

        textView.removeAllSpans(HighlightTextView.HighlightSpan.class);

        if (!CollectionUtil.isEmpty(highlightList)) {
            // 하이라이트 그리기
            for (Highlight item : highlightList) {
                textView.addSpan(new HighlightTextView.HighlightSpan(item, () ->
                        vm.setSelectedItem(item)));
            }
        }
    }

    /**
     * 메모 span 추가
     */
    @BindingAdapter({"memoItems"})
    public static void setMemoItems(@NonNull final HighlightTextView textView,
                                    @Nullable final List<Memo> memoList) {

        textView.removeAllSpans(HighlightTextView.MemoSpan.class);

        if (!CollectionUtil.isEmpty(memoList)) {
            // 메모 그리기
            for (Memo item : memoList) {
                textView.addSpan(new HighlightTextView.MemoSpan(item));
            }
        }
    }


    /**
     * SelectableTextView selection tow-way binding
     */
    @BindingAdapter({"selection"})
    public static void setSelection(@NonNull final SelectableTextView textView,
                                    @NonNull final Pair<String, String> selection) {
        textView.setSelection(selection);
    }

    @InverseBindingAdapter(attribute = "selection", event = "selectionChanged")
    public static Pair<String, String> getSelection(@NonNull final SelectableTextView textView) {
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


    /**
     * SelectableTextView selection tow-way binding
     */
    @BindingAdapter({"android:enabled"})
    public static void setEnabled(@NonNull final MemoEditText editText,
                                  @NonNull final Boolean isEnabled) {
        editText.setEnabled(isEnabled);
    }

    @InverseBindingAdapter(attribute = "android:enabled", event = "enabledChanged")
    public static Boolean getEnabled(@NonNull final MemoEditText editText) {
        return editText.isEnabled();
    }

    @SuppressLint({"ClickableViewAccessibility", "CheckResult"})
    @BindingAdapter("enabledChanged")
    public static void setEnabledListener(@NonNull final MemoEditText textView,
                                          @Nullable final InverseBindingListener listener) {
        textView.getEnabledChangeStream()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(__ -> {
                    if (listener != null) {
                        listener.onChange();
                    }
                }, Throwable::printStackTrace);
    }

}
