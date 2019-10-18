package com.kirinsidea.ui.webdialog;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.kirinsidea.R;
import com.kirinsidea.data.source.local.room.entity.Folder;
import com.kirinsidea.ui.listener.ItemClickListener;

public class FolderListAdapter extends ListAdapter<Folder, FolderItemViewHolder> {

    private ItemClickListener<Folder> itemClickListener;

    private static final int Top = 0;
    private static final int Other = 1;

    FolderListAdapter(ItemClickListener<Folder> itemClickListener) {
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
        final Folder item = getItem(holder.getAdapterPosition());
        holder.bindTo(item);

        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(__ ->
                    itemClickListener.onItemClick(item));
        }
    }

    private static final DiffUtil.ItemCallback<Folder> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Folder>() {
                @Override
                public boolean areItemsTheSame(@NonNull Folder oldItem, @NonNull Folder newItem) {
                    return oldItem.getName() == newItem.getName();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Folder oldItem, @NonNull Folder newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
