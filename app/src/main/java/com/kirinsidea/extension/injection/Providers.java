package com.kirinsidea.extension.injection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.repository.BookmarkRepositoryImpl;
import com.kirinsidea.data.repository.FolderRepository;
import com.kirinsidea.data.repository.FolderRepositoryImpl;
import com.kirinsidea.data.repository.LoginRepository;
import com.kirinsidea.data.repository.LoginRepositoryImpl;
import com.kirinsidea.data.source.local.room.AppDatabase;
import com.kirinsidea.data.source.local.room.dao.BookmarkDao;
import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.remote.kirin.RetrofitClient;
import com.kirinsidea.data.source.remote.thirdparty.firebase.FirebaseAuthApi;
import com.kirinsidea.data.source.remote.thirdparty.google.GoogleLoginApi;
import com.kirinsidea.ui.BaseViewModel;

public abstract class Providers {

    @NonNull
    public static <VM extends BaseViewModel> VM getViewModel(
            @NonNull final FragmentActivity activity,
            @NonNull final Class<VM> vmClass) {

        return Injectors.initViewModel(activity,
                ViewModelProviders.of(activity).get(vmClass));
    }

    @NonNull
    static LoginRepository getLoginRepository() {
        return LoginRepositoryImpl.getInstance();
    }

    @NonNull
    static BookmarkRepository getBookmarkRepository() {
        return BookmarkRepositoryImpl.getInstance();
    }

    @NonNull
    static FolderRepository getFolderRepository() {
        return FolderRepositoryImpl.getInstance();
    }

    @NonNull
    static RetrofitClient getRetrofitClient() {
        return RetrofitClient.getInstance();
    }

    @NonNull
    static BookmarkDao getBookmarkDao(@NonNull final Context context) {
        return AppDatabase.getDatabase(context).bookmarkDao();
    }

    @NonNull
    static FolderDao getFolderDao(@NonNull final Context context) {
        return AppDatabase.getDatabase(context).folderDao();
    }

    @NonNull
    static FirebaseAuthApi getFirebaseAuthApi() {
        return FirebaseAuthApi.getInstance();
    }

    @NonNull
    static GoogleLoginApi getGoogleLoginApi(@NonNull final Context context) {

        final GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(com.kirinsidea.R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        return GoogleLoginApi.getInstance(GoogleSignIn
                .getClient(context.getApplicationContext(), options));
    }
}
