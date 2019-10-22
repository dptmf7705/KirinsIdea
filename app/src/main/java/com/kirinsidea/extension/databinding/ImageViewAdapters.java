package com.kirinsidea.extension.databinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ImageViewAdapters {

    @BindingAdapter({"image"})
    public static void loadImageFromUrl(@NonNull final AppCompatImageView button,
                                        @Nullable final String imageUrl) {
        if (imageUrl == null) {
            return;
        }

        Glide.with(button).load(imageUrl).into(button);
    }
}
