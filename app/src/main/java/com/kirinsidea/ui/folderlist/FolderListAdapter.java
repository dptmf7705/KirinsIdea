package com.kirinsidea.ui.folderlist;

import android.view.View;

import androidx.annotation.NonNull;

import com.kirinsidea.R;
import com.kirinsidea.databinding.ItemWebBottomsheetBinding;
import com.kirinsidea.ui.base.BaseListAdapter;
import com.kirinsidea.ui.base.BaseViewHolder;
import com.kirinsidea.ui.listener.ItemClickListener;

public class FolderListAdapter extends BaseListAdapter<Folder, FolderListAdapter.ViewHolder> {

    public FolderListAdapter(ItemClickListener<Folder> itemClickListener) {
        super(Folder.DIFF_CALLBACK, itemClickListener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_web_bottomsheet;
    }

    @Override
    protected ViewHolder onCreateViewHolder(@NonNull View itemView, int viewType) {
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Folder item, int position) {
        holder.getBinding().setItem(item);
    }

    static final class ViewHolder extends BaseViewHolder<ItemWebBottomsheetBinding> {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
