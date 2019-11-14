package com.kirinsidea.ui.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected final B binding;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public B getBinding() {
        return binding;
    }
}
