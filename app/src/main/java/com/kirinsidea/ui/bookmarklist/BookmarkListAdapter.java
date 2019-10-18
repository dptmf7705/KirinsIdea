package com.kirinsidea.ui.bookmarklist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.kirinsidea.R;
import com.kirinsidea.ui.listener.ItemClickListener;

public class BookmarkListAdapter extends PagedListAdapter<BookmarkItem, BookmarkItemViewHolder> {

    private ItemClickListener<BookmarkItem> itemClickListener;

    BookmarkListAdapter(ItemClickListener<BookmarkItem> itemClickListener) {
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
        final BookmarkItem item = getItem(holder.getAdapterPosition());
        holder.bindTo(item);

        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(__ ->
                    itemClickListener.onItemClick(item));
        }
    }


    private static final DiffUtil.ItemCallback<BookmarkItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<BookmarkItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull BookmarkItem oldItem, @NonNull BookmarkItem newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull BookmarkItem oldItem, @NonNull BookmarkItem newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
