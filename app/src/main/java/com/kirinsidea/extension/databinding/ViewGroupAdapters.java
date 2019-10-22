package com.kirinsidea.extension.databinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewGroupAdapters {

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
     * RecyclerView orientation 바인딩
     *
     * @value LinearLayoutManager.HORIZONTAL
     * @value LinearLayoutManager.VERTICAL
     */
    @BindingAdapter({"orientation"})
    public static void setOrientation(@NonNull final RecyclerView recyclerView,
                                      final int orientation) {
        if (recyclerView.getLayoutManager() == null) {
            return;
        }

        ((LinearLayoutManager) recyclerView.getLayoutManager())
                .setOrientation(orientation);
    }
}
