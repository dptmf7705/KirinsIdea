package com.kirinsidea.ui.bookmarklist;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.R;
import com.kirinsidea.databinding.FragmentBookmarkListBinding;
import com.kirinsidea.extension.Injection;
import com.kirinsidea.ui.BaseFragment;
import com.kirinsidea.ui.bookmark.BookmarkActivity;

public class BookmarkListFragment extends BaseFragment<FragmentBookmarkListBinding> {

    public static BookmarkListFragment newInstance() {
        return new BookmarkListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bookmark_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initViews();
    }

    private void initViewModel() {
        binding.setVm(Injection.provideBookmarkListViewModel(getActivity()));
    }

    private void initViews() {
        binding.recyclerBookmark.setAdapter(new BookmarkListAdapter(
                item -> startBookmarkActivity(item.getId())));
    }

    private void startBookmarkActivity(int id) {
        startActivity(BookmarkActivity.getLaunchIntent(getContext(), id));
    }

}
