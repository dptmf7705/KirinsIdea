package com.kirinsidea.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public abstract class BottomSheetFragment<B extends ViewDataBinding> extends BottomSheetDialogFragment {

    protected B binding;
    protected FragmentActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (FragmentActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onDetach() {
        this.binding = null;
        this.activity = null;
        super.onDetach();
    }

    protected abstract int getLayoutId();
}
