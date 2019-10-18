package com.kirinsidea.extension.databinding;

import android.graphics.Bitmap;
import android.text.Html;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.BindingAdapter;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BindingAdapters {

    /**
     * RecyclerView - items 바인딩
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter({"items"})
    public static <T, VH extends RecyclerView.ViewHolder> void setItems(
            @NonNull final RecyclerView recyclerView,
            @Nullable final List<T> items) {

        final ListAdapter<T, VH> adapter =
                (ListAdapter<T, VH>) recyclerView.getAdapter();

        // List 의 변경을 알리기 위해 새로운 리스트 생성
        if (adapter != null) {
            adapter.submitList(items == null ? null : new ArrayList<>(items));
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"items"})
    public static <T, VH extends RecyclerView.ViewHolder> void setItems(
            @NonNull final RecyclerView recyclerView,
            @Nullable final PagedList<T> items) {
        final PagedListAdapter<T, VH> adapter =
                (PagedListAdapter<T, VH>) recyclerView.getAdapter();

        if (adapter != null) {
            adapter.submitList(items);
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
        if (recyclerView.getLayoutManager() == null) {
            return;
        }

        ((LinearLayoutManager) recyclerView.getLayoutManager())
                .setOrientation(orientation);
    }

    @BindingAdapter("image")
    public static void loadImageFromBitmap(@NonNull final AppCompatImageView imageView,
                                           @Nullable final Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }

        Glide.with(imageView).load(bitmap).into(imageView);
    }

    @BindingAdapter({"image"})
    public static void loadImageFromUri(@NonNull final AppCompatImageView button,
                                        @Nullable final String imageUrl) {
        if (imageUrl == null) {
            return;
        }

        Glide.with(button).load(imageUrl).into(button);
    }

    @BindingAdapter({"image"})
    public static void loadImageFromResource(@NonNull final AppCompatImageView imageView,
                                             final int resId) {
        Glide.with(imageView).load(resId).into(imageView);
    }

    @BindingAdapter({"html"})
    public static void displayHtmlText(@NonNull final AppCompatTextView textView,
                                       @Nullable final CharSequence html) {
        if (html == null || TextUtils.isEmpty(html)) {
            return;
        }

        textView.setText(Html.fromHtml(html.toString()));
    }

    @BindingAdapter({"active"})
    public static void setActive(@NonNull final AppCompatTextView textView,
                                 final boolean isActive) {
        textView.setActivated(isActive);
    }
}
