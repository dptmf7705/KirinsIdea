package com.kirinsidea.extension.injection;

import android.content.Context;

import androidx.annotation.NonNull;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.repository.FolderRepository;
import com.kirinsidea.data.repository.LoginRepository;
import com.kirinsidea.ui.BaseViewModel;
import com.kirinsidea.ui.bookmark.BookmarkViewModel;
import com.kirinsidea.ui.bookmarklist.BookmarkListViewModel;
import com.kirinsidea.ui.login.LoginViewModel;
import com.kirinsidea.ui.webdialog.AddNewBookmarkViewModel;

abstract class Injectors {

    @NonNull
    static <VM extends BaseViewModel> VM initViewModel(
            @NonNull final Context context,
            @NonNull final VM viewModel) {

        if (viewModel instanceof LoginViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(context, Providers.getLoginRepository()));
        } else if (viewModel instanceof BookmarkViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(context, Providers.getBookmarkRepository()));
        } else if (viewModel instanceof BookmarkListViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(context, Providers.getBookmarkRepository()));
        } else if (viewModel instanceof AddNewBookmarkViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    initRepository(context, Providers.getBookmarkRepository()),
                    initRepository(context, Providers.getFolderRepository()));
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NonNull
    private static <R extends BaseRepository> R initRepository(
            @NonNull final Context context,
            @NonNull final R repository) {

        if (repository instanceof LoginRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getGoogleLoginApi(context),
                    Providers.getFirebaseAuthApi());
        } else if (repository instanceof BookmarkRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getRetrofitClient(),
                    Providers.getBookmarkDao(context));
        } else if (repository instanceof FolderRepository) {
            //noinspection unchecked
            return (R) repository.init(
                    Providers.getRetrofitClient(),
                    Providers.getFolderDao(context));
        }

        throw new IllegalArgumentException("Unknown Repository class");
    }
}
