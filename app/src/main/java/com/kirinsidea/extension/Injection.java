package com.kirinsidea.extension;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.kirinsidea.R;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.repository.BookmarkRepositoryImpl;
import com.kirinsidea.data.repository.LoginRepository;
import com.kirinsidea.data.repository.LoginRepositoryImpl;
import com.kirinsidea.data.source.local.room.AppDatabase;
import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.remote.firebase.FirebaseAuthApi;
import com.kirinsidea.data.source.remote.google.GoogleLoginApi;
import com.kirinsidea.ui.bookmark.BookmarkViewModel;
import com.kirinsidea.ui.bookmarklist.BookmarkListViewModel;
import com.kirinsidea.ui.login.LoginViewModel;

public class Injection {

    @NonNull
    public static LoginViewModel provideLoginViewModel(@NonNull final FragmentActivity activity) {
        return ViewModelProviders
                .of(activity, new LoginViewModel.Factory(
                        provideLoginRepository(activity)))
                .get(LoginViewModel.class);
    }

    @NonNull
    public static BookmarkViewModel provideBookmarkViewModel(
            @NonNull final FragmentActivity activity) {

        return ViewModelProviders
                .of(activity, new BookmarkViewModel.Factory(
                        provideBookmarkRepository(activity)))
                .get(BookmarkViewModel.class);
    }

    @NonNull
    public static BookmarkListViewModel provideBookmarkListViewModel(
            @NonNull final FragmentActivity activity) {

        return ViewModelProviders
                .of(activity, new BookmarkListViewModel.Factory(
                        provideBookmarkRepository(activity)))
                .get(BookmarkListViewModel.class);
    }

    @NonNull
    private static LoginRepository provideLoginRepository(@NonNull final Context context) {
        return LoginRepositoryImpl.getInstance(
                provideGoogleLoginApi(context),
                provideFirebaseAuthApi());
    }

    @NonNull
    private static GoogleLoginApi provideGoogleLoginApi(@NonNull final Context context) {
        return GoogleLoginApi.getInstance(provideGoogleSignInClient(context));
    }

    @NonNull
    private static FirebaseAuthApi provideFirebaseAuthApi() {
        return FirebaseAuthApi.getInstance();
    }

    @NonNull
    public static BookmarkRepository provideBookmarkRepository(
            @NonNull final Context context) {

        return BookmarkRepositoryImpl.getInstance(
                provideBookmarkDao(context));
    }

    @NonNull
    private static BookmarkDao provideBookmarkDao(
            @NonNull final Context context) {

        return provideRoomDatabase(context).bookmarkDao();
    }

    @NonNull
    private static AppDatabase provideRoomDatabase(
            @NonNull final Context context) {

        return AppDatabase.getDatabase(context);
    }

    @NonNull
    private static GoogleSignInClient provideGoogleSignInClient(
            @NonNull final Context context) {

        GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        return GoogleSignIn.getClient(context.getApplicationContext(), options);
    }
}
