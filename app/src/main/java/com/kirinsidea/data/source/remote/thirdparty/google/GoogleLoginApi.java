package com.kirinsidea.data.source.remote.thirdparty.google;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import io.reactivex.Single;

public class GoogleLoginApi {
    private volatile static GoogleLoginApi INSTANCE = null;

    public static GoogleLoginApi getInstance(@NonNull final GoogleSignInClient signInClient) {
        if (INSTANCE == null) {
            synchronized (GoogleLoginApi.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GoogleLoginApi(signInClient);
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    private GoogleSignInClient signInClient;

    private GoogleLoginApi(@NonNull final GoogleSignInClient signInClient) {
        this.signInClient = signInClient;
    }

    @NonNull
    public Single<Intent> getLoginIntent() {
        return Single.just(signInClient.getSignInIntent());
    }

    public Single<AuthCredential> getAuthCredential(@NonNull final Intent data) {
        return Single.create(emitter -> {
            try {
                final GoogleSignInAccount account = GoogleSignIn
                        .getSignedInAccountFromIntent(data)
                        .getResult(ApiException.class);

                if (account != null && account.getIdToken() != null) {
                    emitter.onSuccess(GoogleAuthProvider
                            .getCredential(account.getIdToken(), null));
                }
            } catch (ApiException e) {
                emitter.onError(e);
            }
        });

    }
}
