package com.kirinsidea.ui.bookmarklist;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kirinsidea.R;
import com.kirinsidea.databinding.FragmentBookmarkListBinding;
import com.kirinsidea.extension.injection.Providers;
import com.kirinsidea.ui.base.BaseFragment;
import com.kirinsidea.ui.bookmark.BookmarkActivity;
import com.kirinsidea.ui.folderlist.Folder;
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
        //폴더 이름 수정
//        binding.getFolderlistvm().getIsEdit().observe(this, isEditMode ->
//                binding.getFolderlistvm().changeFolderName());

    }

    private void initViews() {
        binding.recyclerBookmark.setItemAnimator(null);
//        binding.recyclerFolderList.setItemAnimator(null);
//        binding.recyclerFolderList.scrollToPosition(0);

        binding.recyclerBookmark.setAdapter(new BookmarkListAdapter(
                item -> startBookmarkActivity(item.getId())));
        //폴더별 북마크 리스트
        binding.recyclerFolderList.setAdapter(new FolderListDrawerAdapter(
                item -> {
                    binding.getFolderlistvm().toggleDrawer(false);
                    binding.getFolderlistvm().setSelectedItem(item);
                },
                item -> {
                    binding.getFolderlistvm().clickFavorite(item);
                    //이거 아님
//                    binding.recyclerFolderList.scrollToPosition(0);
                },
                item -> {
                    binding.getFolderlistvm().toggleDrawer(true);
                    binding.getFolderlistvm().setLongClick(true);
                    binding.getFolderlistvm().setIsLongClick(item);
                    return true;
                }
        ));
        binding.getFolderlistvm().getSelectedItem().observe(this
                , item -> binding.getVm().loadBookmarkListSelected(item.getId()));
        //전체
        binding.folderDefault.tvFolder.setOnClickListener(v -> {
            binding.getFolderlistvm().toggleDrawer(false);
            Folder allBookmark = new Folder("전체");
            binding.getFolderlistvm().setSelectedItem(allBookmark);
            binding.getVm().loadBookmarkListSelected("-1");
        });
    }

    private void startBookmarkActivity(String id) {
        startActivity(BookmarkActivity.getLaunchIntent(getContext(), id));
    }
}
