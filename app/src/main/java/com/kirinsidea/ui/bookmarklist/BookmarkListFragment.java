package com.kirinsidea.ui.bookmarklist;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.R;
import com.kirinsidea.data.source.local.room.entity.BookmarkEntity;
import com.kirinsidea.data.source.local.room.entity.FolderEntity;
import com.kirinsidea.databinding.FragmentBookmarkListBinding;
import com.kirinsidea.extension.injection.Providers;
import com.kirinsidea.ui.BaseFragment;
import com.kirinsidea.ui.bookmark.BookmarkActivity;
import com.kirinsidea.ui.folderlist.FolderListDrawerAdapter;
import com.kirinsidea.ui.folderlist.FolderListViewModel;

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
        binding.setVm(Providers.getViewModel(this, BookmarkListViewModel.class));
        binding.setFolderlistvm(Providers.getViewModel(this, FolderListViewModel.class));
        binding.getFolderlistvm().getFolderId().observe(this, Integer ->
                binding.getFolderlistvm().loadFolderList());
    }

    private void initViews() {
        binding.recyclerBookmark.setAdapter(new BookmarkListAdapter(
                item -> startBookmarkActivity(item.getId())));
        //폴더별 북마크 리스트
        binding.recyclerFolderList.setAdapter(new FolderListDrawerAdapter(
                item -> {
                    binding.getFolderlistvm().toggleDrawer(false);
                    binding.getFolderlistvm().setSelectedItem(item);
                },
                item -> binding.getFolderlistvm().clickFavorite(item)
        ));
        binding.folderDefault.tvFolder.setOnClickListener(v -> {
            binding.getFolderlistvm().toggleDrawer(false);
            FolderEntity allBookmark = new FolderEntity("전체");
            binding.getFolderlistvm().setSelectedItem(allBookmark);
            binding.getVm().loadBookmarkListSelected(allBookmark);
//            binding.getVm().loadBookmarkList();
        });
        binding.getFolderlistvm().getSelectedItem().observe(this, item -> {
            binding.getVm().loadBookmarkListSelected(item);
//            binding.getVm().loadBookmarkListByFolderId(item);
        });
    }

    private void startBookmarkActivity(int id) {
        startActivity(BookmarkActivity.getLaunchIntent(getContext(), id));
    }
}
