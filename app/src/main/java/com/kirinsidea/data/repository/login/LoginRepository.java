package com.kirinsidea.data.repository.login;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.kirinsidea.data.repository.BaseRepository;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface LoginRepository extends BaseRepository {

    @NonNull
    Single<Intent> observeGoogleLoginIntent();

    @NonNull
    Completable observeLoginWithGoogle(@NonNull final Intent intent);
}
