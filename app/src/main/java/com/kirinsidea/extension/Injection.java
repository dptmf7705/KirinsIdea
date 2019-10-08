package com.kirinsidea.extension;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.kirinsidea.R;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.repository.BookmarkRepositoryImpl;
import com.kirinsidea.data.repository.FolderRepository;
import com.kirinsidea.data.repository.FolderRepositoryImpl;
import com.kirinsidea.data.repository.LoginRepository;
import com.kirinsidea.data.repository.LoginRepositoryImpl;
import com.kirinsidea.data.source.local.room.AppDatabase;
import com.kirinsidea.data.source.remote.RetrofitClient;
import com.kirinsidea.data.source.remote.firebase.FirebaseAuthApi;
import com.kirinsidea.data.source.remote.google.GoogleLoginApi;
import com.kirinsidea.ui.BaseViewModel;
import com.kirinsidea.ui.bookmark.BookmarkViewModel;
import com.kirinsidea.ui.bookmarklist.BookmarkListViewModel;
import com.kirinsidea.ui.login.LoginViewModel;
import com.kirinsidea.ui.webdialog.AddNewBookmarkViewModel;

import java.util.Objects;

public class Injection {

    @NonNull
    public static <VM extends BaseViewModel> VM provideViewModel(
            @NonNull final FragmentActivity activity,
            @NonNull final Class<VM> vmClass) {

        return initViewModel(activity,
                ViewModelProviders.of(activity).get(vmClass));
    }

    @NonNull
    public static <VM extends BaseViewModel> VM provideViewModel(
            @NonNull final Fragment fragment,
            @NonNull final Class<VM> vmClass) {

        return initViewModel(Objects.requireNonNull(fragment.getContext()),
                ViewModelProviders.of(fragment).get(vmClass));
    }

    private static <VM extends BaseViewModel> VM initViewModel(
            @NonNull final Context context,
            @NonNull final VM viewModel) {

        if (viewModel instanceof LoginViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(provideLoginRepository(context));
        } else if (viewModel instanceof BookmarkViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(provideBookmarkRepository(context));
        } else if (viewModel instanceof BookmarkListViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(provideBookmarkRepository(context));
        } else if (viewModel instanceof AddNewBookmarkViewModel) {
            //noinspection unchecked
            return (VM) viewModel.init(
                    provideBookmarkRepository(context),
                    provideFolderRepository(context));
        }
        
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

    @NonNull
    private static LoginRepository provideLoginRepository(@NonNull final Context context) {
        return LoginRepositoryImpl.getInstance(
                provideGoogleLoginApi(context),
                provideFirebaseAuthApi());
    }

    @NonNull
    private static BookmarkRepository provideBookmarkRepository(@NonNull final Context context) {
        return BookmarkRepositoryImpl.getInstance(
                provideRetrofitClient(),
                provideRoomDatabase(context).bookmarkDao());
    }

    @NonNull
    private static FolderRepository provideFolderRepository(@NonNull final Context context) {
        return FolderRepositoryImpl.getInstance(
                provideRetrofitClient(),
                provideRoomDatabase(context).folderDao());
    }

    @NonNull
    private static RetrofitClient provideRetrofitClient() {
        return RetrofitClient.getInstance();
    }

    @NonNull
    private static AppDatabase provideRoomDatabase(@NonNull final Context context) {
        return AppDatabase.getDatabase(context);
    }

    @NonNull
    private static FirebaseAuthApi provideFirebaseAuthApi() {
        return FirebaseAuthApi.getInstance();
    }

    @NonNull
    private static GoogleLoginApi provideGoogleLoginApi(@NonNull final Context context) {

        final GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        return GoogleLoginApi.getInstance(GoogleSignIn
                .getClient(context.getApplicationContext(), options));
    }
}
