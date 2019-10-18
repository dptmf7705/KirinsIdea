package com.kirinsidea.ui.folderList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.kirinsidea.R;
import com.kirinsidea.data.source.local.room.entity.FolderEntity;
import com.kirinsidea.ui.listener.ItemClickListener;

public class FolderListAdapter extends ListAdapter<FolderEntity, FolderItemViewHolder> {

    private ItemClickListener<FolderEntity> itemClickListener;

    private static final int Top = 0;
    private static final int Other = 1;

    public FolderListAdapter(ItemClickListener<FolderEntity> itemClickListener) {
        super(DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return Top;
        } else {
            return Other;
        }
    }

    @NonNull
    @Override
    public FolderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0) {
            return new FolderItemViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_web_bottomsheet, parent, false));
        } else{
            return new FolderItemViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_web_bottomsheet, parent, false));
        }
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
                    return oldItem.getName() == newItem.getName();
                }

                @Override
                public boolean areContentsTheSame(@NonNull FolderEntity oldItem, @NonNull FolderEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
