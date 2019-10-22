package com.kirinsidea.extension.databinding;

import android.graphics.drawable.GradientDrawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.kirinsidea.R;
import com.kirinsidea.ui.highlight.HighlightColor;

public class AnyViewAdapters {

    @BindingAdapter({"active"})
    public static void setActive(@NonNull final View view,
                                 final boolean isActive) {
        view.setActivated(isActive);
    }

    /**
     * 하이라이트 색상 선택 패널 바인딩
     *
     * @value highlightColor    하이라이트 색 바인딩
     * @value selected          테두리 색 바인딩
     */
    @BindingAdapter({"highlightColor", "selected"})
    public static void setHighlightColor(@NonNull final View view,
                                         @NonNull final HighlightColor color,
                                         final boolean selected) {
        GradientDrawable drawable = (GradientDrawable) view.getBackground();
        drawable.setColor(view.getResources().getColor(color.getColorResId()));
        drawable.setStroke(8, view.getResources().getColor(
                selected ? R.color.colorWhite : R.color.colorBlack));
    }
}
