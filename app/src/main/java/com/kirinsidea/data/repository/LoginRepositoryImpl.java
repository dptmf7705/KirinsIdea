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

    private LoginRepositoryImpl() { }

    private FirebaseAuthApi firebaseApi;
    private GoogleLoginApi googleApi;

    @NonNull
    @Override
    public BaseRepository init(@NonNull Object... dataSources) {
        this.firebaseApi = (FirebaseAuthApi) dataSources[0];
        this.googleApi = (GoogleLoginApi) dataSources[1];
        return this;
    }

    @NonNull
    @Override
    public Single<Intent> observeGoogleLoginIntent() {
        return googleApi.observeLoginIntent();
    }

    @NonNull
    @Override
    public Completable observeLoginWithGoogle(@NonNull Intent intent) {
        return googleApi.observeAuthCredential(intent)
                .flatMapCompletable(this::observeFirebaseAuth);
    }

    @NonNull
    private Completable observeFirebaseAuth(@NonNull AuthCredential credential) {
        return firebaseApi.observeFirebaseAuth(credential);
    }
}
