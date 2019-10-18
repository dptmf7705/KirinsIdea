package com.kirinsidea.ui.bookmarklist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.kirinsidea.R;
import com.kirinsidea.data.source.local.room.entity.BookmarkEntity;
import com.kirinsidea.ui.listener.ItemClickListener;

public class BookmarkListAdapter extends PagedListAdapter<BookmarkEntity, BookmarkItemViewHolder> {

    private ItemClickListener<BookmarkEntity> itemClickListener;

    BookmarkListAdapter(ItemClickListener<BookmarkEntity> itemClickListener) {
        super(DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public BookmarkItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookmarkItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bookmark, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkItemViewHolder holder, int position) {
        final BookmarkEntity item = getItem(holder.getAdapterPosition());
        holder.bindTo(item);

        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(__ ->
                    itemClickListener.onItemClick(item));
        }
    }


    private static final DiffUtil.ItemCallback<BookmarkEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<BookmarkEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull BookmarkEntity oldItem, @NonNull BookmarkEntity newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull BookmarkEntity oldItem, @NonNull BookmarkEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
