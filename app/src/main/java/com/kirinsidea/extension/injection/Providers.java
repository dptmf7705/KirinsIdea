package com.kirinsidea.extension.injection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.kirinsidea.App;
import com.kirinsidea.data.repository.BookmarkRepository;
import com.kirinsidea.data.repository.BookmarkRepositoryImpl;
import com.kirinsidea.data.repository.FolderRepository;
import com.kirinsidea.data.repository.FolderRepositoryImpl;
import com.kirinsidea.data.repository.HighlightRepository;
import com.kirinsidea.data.repository.HighlightRepositoryImpl;
import com.kirinsidea.data.repository.LoginRepository;
import com.kirinsidea.data.repository.LoginRepositoryImpl;
import com.kirinsidea.data.repository.MemoRepository;
import com.kirinsidea.data.repository.MemoRepositoryImpl;
import com.kirinsidea.data.source.local.room.AppDatabase;
import com.kirinsidea.data.source.remote.kirin.RetrofitClient;
import com.kirinsidea.data.source.remote.thirdparty.firebase.FirebaseAuthApi;
import com.kirinsidea.data.source.remote.thirdparty.google.GoogleLoginApi;
import com.kirinsidea.ui.base.BaseViewModel;

public abstract class Providers {

    @NonNull
    public static <VM extends BaseViewModel> VM getViewModel(
            @NonNull final FragmentActivity activity,
            @NonNull final Class<VM> vmClass) {

        return Injectors.initViewModel(
                ViewModelProviders.of(activity).get(vmClass));
    }

    @NonNull
    public static <VM extends BaseViewModel> VM getViewModel(
            @NonNull final Fragment fragment,
            @NonNull final Class<VM> vmClass) {

        return Injectors.initViewModel(
                ViewModelProviders.of(fragment).get(vmClass));
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
    static HighlightRepository getHighlightRepository() {
        return HighlightRepositoryImpl.getInstance();
    }

    @NonNull
    static MemoRepository getMemoRepository() {
        return MemoRepositoryImpl.getInstance();
    }

    @NonNull
    static <T> T getRoomDao(@NonNull final Class<T> daoClass) {
        return AppDatabase.getInstance(App.instance().getContext()).create(daoClass);
    }

    @NonNull
    static <T> T getRetrofitApi(@NonNull final Class<T> apiClass) {
        return RetrofitClient.getInstance().create(apiClass);
    }

    @NonNull
    static FirebaseAuthApi getFirebaseAuthApi() {
        return FirebaseAuthApi.getInstance();
    }

    @NonNull
    static GoogleLoginApi getGoogleLoginApi() {

        final Context context = App.instance().getContext();

        final GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(com.kirinsidea.R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build();

        return GoogleLoginApi.getInstance(
                GoogleSignIn.getClient(context, options));
    }
}
