package com.kirinsidea.ui.folderlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.kirinsidea.R;
import com.kirinsidea.data.source.local.room.entity.FolderEntity;
import com.kirinsidea.ui.listener.ItemClickListener;

public class FolderListDrawerAdapter extends ListAdapter<FolderEntity, FolderItemDrawerViewHolder> {

    private ItemClickListener<FolderEntity> itemClickListener;
    private ItemClickListener<FolderEntity> favoriteClickListener;

    public FolderListDrawerAdapter(ItemClickListener<FolderEntity> itemClickListener,
                                   ItemClickListener<FolderEntity> favoriteClickListener) {
        super(DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;
        this.favoriteClickListener = favoriteClickListener;
    }

    @NonNull
    @Override
    public FolderItemDrawerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FolderItemDrawerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_folder_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FolderItemDrawerViewHolder holder, int position) {
        final FolderEntity item = getItem(holder.getAdapterPosition());
        holder.bindTo(item);

        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(__ ->
                    itemClickListener.onItemClick(item));
        }

        if (favoriteClickListener != null) {
            holder.getBinding().ivFolderPin.setOnClickListener(__ ->
                    favoriteClickListener.onItemClick(item));
        }
    }


    private static final DiffUtil.ItemCallback<FolderEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<FolderEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull FolderEntity oldItem, @NonNull FolderEntity newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull FolderEntity oldItem, @NonNull FolderEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
