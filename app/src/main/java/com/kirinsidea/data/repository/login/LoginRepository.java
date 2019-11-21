package com.kirinsidea.data.repository.login;

import androidx.annotation.NonNull;

import com.google.firebase.auth.AuthCredential;
import com.kirinsidea.data.repository.BaseRepository;

import io.reactivex.Completable;

public interface LoginRepository extends BaseRepository {

    @NonNull
    Completable observeSignInFirebase(@NonNull AuthCredential credential);
}
