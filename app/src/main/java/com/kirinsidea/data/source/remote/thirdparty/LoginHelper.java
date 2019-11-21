package com.kirinsidea.data.source.remote.thirdparty;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import com.google.firebase.auth.AuthCredential;
import com.kirinsidea.ui.login.NewUser;

import io.reactivex.Single;

public interface LoginHelper {

    @NonNull
    Single<Pair<NewUser, AuthCredential>> startSingIn();

    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);

    @NonNull
    Single<AuthCredential> getFirebaseCredential(@Nullable String token);
}
