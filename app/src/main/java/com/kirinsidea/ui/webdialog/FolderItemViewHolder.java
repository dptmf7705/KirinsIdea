package com.kirinsidea.ui.webdialog;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kirinsidea.data.source.local.room.entity.Folder;
import com.kirinsidea.databinding.ItemWebBottomsheetBinding;

public class FolderItemViewHolder extends RecyclerView.ViewHolder {
    private final ItemWebBottomsheetBinding binding;

    public FolderItemViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }
    public void bindTo(Folder item) {
        binding.setItem(item);
    }
}
