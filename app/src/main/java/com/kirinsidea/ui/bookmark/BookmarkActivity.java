package com.kirinsidea.ui.bookmark;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.R;
import com.kirinsidea.databinding.ActivityBookmarkBinding;
import com.kirinsidea.extension.injection.Providers;
import com.kirinsidea.ui.BaseActivity;
import com.kirinsidea.ui.highlight.Highlight;
import com.kirinsidea.ui.highlight.HighlightColor;
import com.kirinsidea.ui.highlight.HighlightViewModel;
import com.kirinsidea.ui.memo.MemoFragment;
import com.kirinsidea.ui.memo.MemoViewModel;

import gun0912.tedkeyboardobserver.TedRxKeyboardObserver;

@SuppressLint("CheckResult")
public class BookmarkActivity extends BaseActivity<ActivityBookmarkBinding> {
    private static final String EXTRA_BOOKMARK_ID = "EXTRA_BOOKMARK_ID";
    private static final String TAG_MEMO_BOTTOM_SHEET = "TAG_MEMO_BOTTOM_SHEET";
    private int bookmarkId;

    public static Intent getLaunchIntent(@NonNull final Context context,
                                         final int bookmarkId) {
        Intent intent = new Intent(context, BookmarkActivity.class);
        intent.putExtra(EXTRA_BOOKMARK_ID, bookmarkId);
        return intent;
    }

    private void getLaunchIntentData() {
        bookmarkId = getIntent().getIntExtra(EXTRA_BOOKMARK_ID, 0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bookmark;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLaunchIntentData();
        initViewModels();
        initViews();
    }

    private void initViews() {
        binding.ivMemo.setOnClickListener(__ -> {
            final Highlight highlight = binding.getHighlightVm().getSelectedItem().getValue();
            if (highlight == null) {
                binding.getHighlightVm().setHighlightColor(HighlightColor.YELLOW);
            }
            binding.getMemoVm().setIsMemoOpen(Boolean.TRUE);
        });
        new TedRxKeyboardObserver(this)
                .listen()
                .subscribe(binding.getMemoVm()::setIsEditMode, Throwable::printStackTrace);
    }

    private void initViewModels() {
        binding.setBookmarkVm(Providers.getViewModel(this, BookmarkViewModel.class));
        binding.getBookmarkVm().loadBookmark(bookmarkId);
        binding.setHighlightVm(Providers.getViewModel(this, HighlightViewModel.class));
        binding.getHighlightVm().loadHighlightList(bookmarkId);
        binding.setMemoVm(Providers.getViewModel(this, MemoViewModel.class));
        binding.getMemoVm().loadMemoList(bookmarkId);

        binding.getHighlightVm().getSelectedItem().observe(this, highlight -> {
            binding.getMemoVm().setHighlight(highlight);
        });

        binding.getMemoVm().getIsMemoOpen().observe(this, isMemoOpen -> {
            Void aVoid = Boolean.TRUE.equals(isMemoOpen) ? openMemoFragment() : closeMemoFragment();
        });
    }

    @Nullable
    private Void openMemoFragment() {
        MemoFragment.newInstance().show(getSupportFragmentManager(), TAG_MEMO_BOTTOM_SHEET);
        return null;
    }

    @Nullable
    private Void closeMemoFragment() {
        MemoFragment fragment = (MemoFragment) getSupportFragmentManager().findFragmentByTag(TAG_MEMO_BOTTOM_SHEET);
        if (fragment != null) {
            fragment.dismiss();
        }
        return null;
    }
}
