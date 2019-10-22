package com.kirinsidea.extension.databinding;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

public class AnyViewAdapters {

    @BindingAdapter({"active"})
    public static void setActive(@NonNull final View view,
                                 final boolean isActive) {
        view.setActivated(isActive);
    }
}
