package com.kirinsidea.extension.databinding;

import android.text.Html;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;

public class TextViewAdapters {

    @BindingAdapter({"html"})
    public static void setTextFromHtml(@NonNull final AppCompatTextView textView,
                                       @Nullable final CharSequence html) {
        if (html == null || TextUtils.isEmpty(html)) {
            return;
        }

        textView.setText(Html.fromHtml(html.toString()));
    }
}
