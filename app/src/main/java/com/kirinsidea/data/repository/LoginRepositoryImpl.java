package com.kirinsidea.data.repository;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.auth.AuthCredential;
import com.kirinsidea.data.source.remote.thirdparty.firebase.FirebaseAuthApi;
import com.kirinsidea.data.source.remote.thirdparty.google.GoogleLoginApi;

import io.reactivex.Completable;
import io.reactivex.Single;

public class LoginRepositoryImpl implements LoginRepository {
    private static class LazyHolder {
        private static final LoginRepository INSTANCE = new LoginRepositoryImpl();
    }

    @NonNull
    public static LoginRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private LoginRepositoryImpl() {
    }

    private FirebaseAuthApi firebaseRemoteDataSource;
    private GoogleLoginApi googleRemoteDataSource;

    @NonNull
    @Override
    public BaseRepository init(@NonNull Object... dataSources) {
        this.firebaseRemoteDataSource = (FirebaseAuthApi) dataSources[0];
        this.googleRemoteDataSource = (GoogleLoginApi) dataSources[1];
        return this;
    }

    @NonNull
    @Override
    public Single<Intent> observeGoogleLoginIntent() {
        return googleRemoteDataSource.getLoginIntent();
    }

    @NonNull
    @Override
    public Completable observeLoginWithGoogle(@NonNull Intent intent) {
        return googleRemoteDataSource.getAuthCredential(intent)
                .flatMapCompletable(this::observeFirebaseAuth);
    }

    @NonNull
    private Completable observeFirebaseAuth(@NonNull AuthCredential credential) {
        return firebaseRemoteDataSource.LoginWithCredential(credential);
    }
}
