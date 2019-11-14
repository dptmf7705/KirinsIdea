package com.kirinsidea.ui.bookmarklist;

import android.view.View;

import androidx.annotation.NonNull;

import com.kirinsidea.R;
import com.kirinsidea.databinding.ItemBookmarkBinding;
import com.kirinsidea.ui.base.BasePagedListAdapter;
import com.kirinsidea.ui.base.BaseViewHolder;
import com.kirinsidea.ui.listener.ItemClickListener;

public class BookmarkListAdapter extends BasePagedListAdapter<BookmarkItem, BookmarkListAdapter.ViewHolder> {

    BookmarkListAdapter(ItemClickListener<BookmarkItem> itemClickListener) {
        super(BookmarkItem.DIFF_CALLBACK, itemClickListener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_bookmark;
    }

    @Override
    protected ViewHolder onCreateViewHolder(@NonNull View itemView, int viewType) {
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder,
                                    @NonNull BookmarkItem item,
                                    int position) {
        holder.getBinding().setItem(item);
    }

    static final class ViewHolder extends BaseViewHolder<ItemBookmarkBinding> {

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
