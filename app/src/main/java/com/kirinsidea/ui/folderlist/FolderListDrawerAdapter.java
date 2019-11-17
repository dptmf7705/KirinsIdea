package com.kirinsidea.ui.folderlist;

import android.view.View;

import androidx.annotation.NonNull;

import com.kirinsidea.R;
import com.kirinsidea.databinding.ItemFolderListBinding;
import com.kirinsidea.ui.base.BaseListAdapter;
import com.kirinsidea.ui.base.BaseViewHolder;
import com.kirinsidea.ui.listener.ItemClickListener;
import com.kirinsidea.ui.listener.ItemLongClickListener;

public class FolderListDrawerAdapter extends BaseListAdapter<Folder, FolderListDrawerAdapter.ViewHolder> {

    public FolderListDrawerAdapter(ItemClickListener<Folder> itemClickListener,
                                   ItemClickListener<Folder> favoriteClickListener,
                                   ItemLongClickListener<Folder> itemLongClickListener) {
        super(Folder.DIFF_CALLBACK, itemClickListener, favoriteClickListener, itemLongClickListener);
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
    }
    static final class ViewHolder extends BaseViewHolder<ItemFolderListBinding> {

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

//
//    @NonNull
//    @Override
//    public FolderItemDrawerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new FolderItemDrawerViewHolder(LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_folder_list, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FolderItemDrawerViewHolder holder, int position) {
//        final Folder item = getItem(holder.getAdapterPosition());
//        holder.bindTo(item);
//
//        if (itemClickListener != null) {
//            holder.itemView.setOnClickListener(__ ->
//                    itemClickListener.onItemClick(item));
//        }
//
//        if (favoriteClickListener != null) {
//            holder.getBinding().ivFolderPin.setOnClickListener(__ ->
//                    favoriteClickListener.onItemClick(item));
//        }
//
//        if (itemLongClickListener != null){
//            holder.itemView.setOnLongClickListener(v -> {
//                itemLongClickListener.onItemLongClick(item);
//                item.Builder.setSelected(!item.isSelected());
//                holder.itemView.setBackgroundResource(item.isSelected() ? R.color.silver : R.color.white);
//                return true;
//            });
//            //test
//            holder.itemView.setOnClickListener(__ ->
//                    itemClickListener.onItemClick(item));
//        }
//    }
}
