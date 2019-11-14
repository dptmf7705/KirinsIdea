package com.kirinsidea.extension.injection;

import androidx.annotation.NonNull;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.bookmark.BookmarkRepository;
import com.kirinsidea.data.repository.folder.FolderRepository;
import com.kirinsidea.data.repository.highlight.HighlightRepository;
import com.kirinsidea.data.repository.login.LoginRepository;
import com.kirinsidea.data.repository.memo.MemoRepository;
import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.local.room.dao.HighlightDao;
import com.kirinsidea.data.source.local.room.dao.MemoDao;
import com.kirinsidea.data.source.remote.kirin.api.BookmarkApi;
import com.kirinsidea.data.source.remote.kirin.api.FileApi;
import com.kirinsidea.data.source.remote.kirin.api.FolderApi;
import com.kirinsidea.ui.base.BaseViewModel;
import com.kirinsidea.ui.bookmark.BookmarkViewModel;
import com.kirinsidea.ui.bookmarklist.BookmarkListViewModel;
import com.kirinsidea.ui.highlight.HighlightViewModel;
import com.kirinsidea.ui.folderlist.FolderListViewModel;
import com.kirinsidea.ui.login.LoginViewModel;
import com.kirinsidea.ui.memo.MemoViewModel;
import com.kirinsidea.ui.newbookmark.NewBookmarkViewModel;

abstract class Injectors {

    @NonNull
    static <VM extends BaseViewModel> VM initViewModel(@NonNull final VM viewModel) {
        if (viewModel instanceof LoginViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(Providers.getLoginRepository()));
        } else if (viewModel instanceof BookmarkViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(Providers.getBookmarkRepository()));
        } else if (viewModel instanceof BookmarkListViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(Providers.getBookmarkRepository()));
        } else if (viewModel instanceof FolderListViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(Providers.getFolderRepository()));
        } else if (viewModel instanceof NewBookmarkViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(Providers.getBookmarkRepository()));
        } else if (viewModel instanceof HighlightViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(Providers.getHighlightRepository()));
        } else if (viewModel instanceof MemoViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(Providers.getMemoRepository()));
        }

        throw new IllegalArgumentException(
                "Unknown View Model class : " + viewModel.getClass().getSimpleName());
    }

    @NonNull
    private static <R extends BaseRepository> R initRepository(@NonNull final R repository) {
        if (repository instanceof LoginRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getFirebaseAuthApi(),
                    Providers.getGoogleLoginApi());
        } else if (repository instanceof BookmarkRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getRoomDao(BookmarkDao.class),
                    Providers.getRetrofitApi(BookmarkApi.class),
                    Providers.getRetrofitApi(FileApi.class));
        } else if (repository instanceof FolderRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getRoomDao(FolderDao.class),
                    Providers.getRetrofitApi(FolderApi.class));
        } else if (repository instanceof HighlightRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getRoomDao(HighlightDao.class));
        } else if (repository instanceof MemoRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getRoomDao(MemoDao.class));
        }

        throw new IllegalArgumentException(
                "Unknown Repository class : " + repository.getClass().getSimpleName());
    }
}
