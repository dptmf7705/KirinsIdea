package com.kirinsidea.ui.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kirinsidea.ui.listener.ItemClickListener;
import com.kirinsidea.ui.listener.ItemLongClickListener;

public abstract class BaseListAdapter<T, VH extends RecyclerView.ViewHolder> extends ListAdapter<T, VH> {

    @Nullable
    protected ItemClickListener<T> itemClickListener;

    public BaseListAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        this(diffCallback, null);
    }

    public BaseListAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback,
                           @Nullable ItemClickListener<T> itemClickListener) {
        super(diffCallback);
        this.itemClickListener = itemClickListener;
    }

    private View inflateItemView(@NonNull ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateViewHolder(inflateItemView(parent, getLayoutId()), viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        final T item = getItem(holder.getAdapterPosition());

        if (itemClickListener != null) {
            holder.itemView.setOnClickListener(__ ->
                    itemClickListener.onItemClick(item));
        }
        onBindViewHolder(holder, item, position);
    }

    public void setItemClickListener(@Nullable ItemClickListener<T> itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    protected abstract int getLayoutId();

    protected abstract VH onCreateViewHolder(@NonNull View itemView, int viewType);

    protected abstract void onBindViewHolder(@NonNull VH holder, @NonNull T item, int position);

}
