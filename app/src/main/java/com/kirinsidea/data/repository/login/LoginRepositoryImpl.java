package com.kirinsidea.data.repository.login;

import androidx.annotation.NonNull;

import com.google.firebase.auth.AuthCredential;
import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.source.remote.thirdparty.firebase.FirebaseAuthApi;

import io.reactivex.Completable;

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

    private FirebaseAuthApi firebaseApi;

    @NonNull
    @Override
    public BaseRepository init(@NonNull Object... dataSources) {
        this.firebaseApi = (FirebaseAuthApi) dataSources[0];
        return this;
    }

    @NonNull
    @Override
    public Completable observeSignInFirebase(@NonNull AuthCredential credential) {
        return firebaseApi.LoginWithCredential(credential);
    }
}
