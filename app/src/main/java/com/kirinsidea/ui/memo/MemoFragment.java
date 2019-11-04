package com.kirinsidea.ui.memo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.R;
import com.kirinsidea.databinding.FragmentMemoBottomsheetBinding;
import com.kirinsidea.extension.injection.Providers;
import com.kirinsidea.ui.BottomSheetFragment;

public class MemoFragment extends BottomSheetFragment<FragmentMemoBottomsheetBinding> {

    public static MemoFragment newInstance() {
        return new MemoFragment();
    }

    public MemoFragment() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_memo_bottomsheet;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModels();
        initViews();
    }

    private void initViewModels() {
        binding.setMemoVm(Providers.getViewModel(activity, MemoViewModel.class));
        binding.getMemoVm().getIsEditMode().observe(this, isEditMode ->
                binding.etContent.post(() -> {
                    ((ViewGroup.MarginLayoutParams) binding.etContent.getLayoutParams())
                            .setMargins(0, 0, 0, isEditMode ? 100 : 0);
                    binding.etContent.requestLayout();
                }));
    }

    private void initViews() {
        binding.ivMenu.setOnClickListener(__ -> openMenuDialog());
    }

    private void openMenuDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setItems(R.array.memoMenu, (dialog, pos) -> {
            switch (pos) {
                case 0:
                    break;
                case 1:
                    break;
                case 2: // 삭제하기
                    binding.etContent.setText(null);
                    dismiss();
                    break;
            }
        });
        builder.create().show();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        binding.getMemoVm().finishEditMemo();
        super.onDismiss(dialog);
    }
}
