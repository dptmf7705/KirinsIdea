package com.kirinsidea.ui.bookmarklist;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kirinsidea.data.source.local.room.entity.BookmarkEntity;
import com.kirinsidea.databinding.ItemBookmarkBinding;

public class BookmarkItemViewHolder extends RecyclerView.ViewHolder {

    private final ItemBookmarkBinding binding;

    public BookmarkItemViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bindTo(BookmarkEntity item) {
        binding.setItem(item);
    }
}
