package com.kirinsidea.extension.injection;

import androidx.annotation.NonNull;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.repository.FolderRepository;
import com.kirinsidea.data.repository.LoginRepository;
import com.kirinsidea.data.repository.MemoRepository;
import com.kirinsidea.ui.BaseViewModel;
import com.kirinsidea.ui.bookmark.BookmarkViewModel;
import com.kirinsidea.ui.bookmark.MemoViewModel;
import com.kirinsidea.ui.bookmarklist.BookmarkListViewModel;
import com.kirinsidea.ui.login.LoginViewModel;
import com.kirinsidea.ui.newbookmark.NewBookmarkViewModel;

abstract class Injectors {

    @NonNull
    static <VM extends BaseViewModel> VM initViewModel(
            @NonNull final VM viewModel) {

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
        } else if (viewModel instanceof NewBookmarkViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(Providers.getBookmarkRepository()),
                    initRepository(Providers.getFolderRepository()));
        } else if (viewModel instanceof MemoViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(Providers.getMemoRepository()));

        }

        throw new IllegalArgumentException(
                Constants.Message.ERROR_INIT_VIEW_MODEL);
    }

    @NonNull
    private static <R extends BaseRepository> R initRepository(
            @NonNull final R repository) {

        if (repository instanceof LoginRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getFirebaseAuthApi(),
                    Providers.getGoogleLoginApi());
        } else if (repository instanceof BookmarkRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getRetrofitClient(),
                    Providers.getBookmarkDao());
        } else if (repository instanceof FolderRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getRetrofitClient(),
                    Providers.getFolderDao());
        } else if (repository instanceof MemoRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getRetrofitClient(),
                    Providers.getMemoDao());
        }

        throw new IllegalArgumentException(
                Constants.Message.ERROR_INIT_REPOSITORY);
    }
}
