package com.kirinsidea.ui.folderlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.kirinsidea.R;
import com.kirinsidea.data.source.entity.FolderEntity;
import com.kirinsidea.ui.listener.ItemClickListener;

public class FolderListAdapter extends ListAdapter<FolderEntity, FolderItemViewHolder> {

    private ItemClickListener<FolderEntity> itemClickListener;

    public FolderListAdapter(ItemClickListener<FolderEntity> itemClickListener) {
        super(DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public FolderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FolderItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_web_bottomsheet, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FolderItemViewHolder holder, int position) {
        final FolderEntity item = getItem(holder.getAdapterPosition());
        holder.bindTo(item);

        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(__ ->
                    itemClickListener.onItemClick(item));
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
