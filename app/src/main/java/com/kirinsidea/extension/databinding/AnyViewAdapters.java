package com.kirinsidea.extension.databinding;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingConversion;

public class AnyViewAdapters {

    @BindingAdapter({"active"})
    public static void setActive(@NonNull final View view,
                                 final boolean isActive) {
        view.setActivated(isActive);
    }

    @BindingAdapter({"android:onTouch"})
    public static void setOnTouchListener(@NonNull final View view,
                                          @NonNull final View.OnTouchListener listener) {
        view.setOnTouchListener(listener);
    }

    @BindingConversion
    public static int setVisibility(final boolean isVisible) {
        return isVisible ? View.VISIBLE : View.GONE;
    }
}
