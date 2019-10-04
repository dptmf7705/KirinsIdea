package com.kirinsidea.data.repository;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.auth.AuthCredential;
import com.kirinsidea.data.source.remote.firebase.FirebaseAuthApi;
import com.kirinsidea.data.source.remote.google.GoogleLoginApi;

import io.reactivex.Completable;
import io.reactivex.Single;

public class LoginRepositoryImpl extends BaseRepository implements LoginRepository {
    private volatile static LoginRepository INSTANCE = null;

    public static LoginRepository getInstance(@NonNull final GoogleLoginApi googleApi,
                                              @NonNull final FirebaseAuthApi firebaseApi) {
        if (INSTANCE == null) {
            synchronized (LoginRepositoryImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginRepositoryImpl(googleApi, firebaseApi);
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    private final GoogleLoginApi googleApi;
    @NonNull
    private final FirebaseAuthApi firebaseApi;

    private LoginRepositoryImpl(@NonNull final GoogleLoginApi googleApi,
                                @NonNull final FirebaseAuthApi firebaseApi) {
        this.googleApi = googleApi;
        this.firebaseApi = firebaseApi;
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
