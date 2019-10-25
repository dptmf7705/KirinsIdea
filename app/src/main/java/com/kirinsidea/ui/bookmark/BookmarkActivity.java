package com.kirinsidea.ui.bookmark;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.R;
import com.kirinsidea.databinding.ActivityBookmarkBinding;
import com.kirinsidea.extension.injection.Providers;
import com.kirinsidea.ui.BaseActivity;
import com.kirinsidea.ui.highlight.HighlightViewModel;

public class BookmarkActivity extends BaseActivity<ActivityBookmarkBinding> {
    private static final String EXTRA_BOOKMARK_ID = "EXTRA_BOOKMARK_ID";
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
    }

    private void initViewModels() {
        binding.setBookmarkVm(Providers.getViewModel(this, BookmarkViewModel.class));
        binding.getBookmarkVm().loadBookmark(bookmarkId);
        binding.setHighlightVm(Providers.getViewModel(this, HighlightViewModel.class));
        binding.getHighlightVm().loadHighlightList(bookmarkId);
    }
}
