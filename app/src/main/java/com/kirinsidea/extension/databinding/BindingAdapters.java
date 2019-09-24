package com.kirinsidea.extension.databinding;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BindingAdapters {

    /**
     * RecyclerView - items 바인딩
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter({"items"})
    public static <T, VH extends RecyclerView.ViewHolder> void setItems(
            @NonNull final RecyclerView recyclerView,
            @Nullable final List<T> items) {
        final ListAdapter<T, VH> adapter = (ListAdapter<T, VH>) recyclerView.getAdapter();
        if (adapter != null) {
            // List 의 변경을 알리기 위해 새로운 리스트 생성
            adapter.submitList(items == null ? null : new ArrayList<>(items));
        }
    }

    /**
     * RecyclerView Layout manager 의 orientation 바인딩
     *
     * @value LinearLayoutManager.HORIZONTAL
     * @value LinearLayoutManager.VERTICAL
     */
    @BindingAdapter({"orientation"})
    public static void setLayoutManager(@NonNull final RecyclerView recyclerView,
                                        final int orientation) {
        ((LinearLayoutManager) Objects.requireNonNull(recyclerView.getLayoutManager()))
                .setOrientation(orientation);
    }

    @BindingAdapter("image")
    public static void loadImageFromBitmap(ImageView imageView, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }

        Glide.with(imageView).load(bitmap).into(imageView);
    }

    @BindingAdapter({"image"})
    public static void loadImageFromUri(ImageView button, String imageUrl) {
        if (imageUrl == null) {
            return;
        }

        Glide.with(button).load(imageUrl).into(button);
    }

    @BindingAdapter({"image"})
    public static void loadImageFromResource(AppCompatImageView imageView, int resId) {
        Glide.with(imageView).load(resId).into(imageView);
    }

}
