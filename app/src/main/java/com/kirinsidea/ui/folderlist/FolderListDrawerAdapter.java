package com.kirinsidea.ui.folderlist;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.R;
import com.kirinsidea.databinding.ItemFolderListBinding;
import com.kirinsidea.ui.base.BaseListAdapter;
import com.kirinsidea.ui.base.BaseViewHolder;
import com.kirinsidea.ui.listener.ItemClickListener;
import com.kirinsidea.ui.listener.ItemLongClickListener;

public class FolderListDrawerAdapter extends BaseListAdapter<Folder, FolderListDrawerAdapter.ViewHolder> {

    @Nullable
    private ItemClickListener<Folder> favoriteClickListener;
    @Nullable
    private ItemLongClickListener<Folder> itemLongClickListener;

    public FolderListDrawerAdapter(ItemClickListener<Folder> itemClickListener,
                                   @Nullable ItemClickListener<Folder> favoriteClickListener,
                                   @Nullable ItemLongClickListener<Folder> itemLongClickListener) {
        super(Folder.DIFF_CALLBACK, itemClickListener);
        this.favoriteClickListener = favoriteClickListener;
        this.itemLongClickListener = itemLongClickListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_folder_list;
    }

    @Override
    protected ViewHolder onCreateViewHolder(@NonNull View itemView, int viewType) {
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Folder item, int position) {
        holder.getBinding().setItem(item);

        if (favoriteClickListener != null) {
            holder.getBinding().ivFolderPin.setOnClickListener(__ ->
                    favoriteClickListener.onItemClick(item));
        }
        if (itemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(v -> {
                itemLongClickListener.onItemLongClick(item);
                item.setSelected(!item.isSelected());
                holder.itemView.setBackgroundResource(item.isSelected() ? R.color.silver : R.color.white);
                return true;
            });
        }
    }

    static final class ViewHolder extends BaseViewHolder<ItemFolderListBinding> {

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
