package com.kirinsidea.data.source.remote.thirdparty.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.Completable;

public class FirebaseAuthApi {
    private static class LazyHolder {
        private static final FirebaseAuthApi INSTANCE = new FirebaseAuthApi();
    }

    public static FirebaseAuthApi getInstance() {
        return LazyHolder.INSTANCE;
    }

    @NonNull
    private final FirebaseAuth firebase;

    private FirebaseAuthApi() {
        this.firebase = FirebaseAuth.getInstance();
    }

    @NonNull
    public Completable observeFirebaseAuth(
            @NonNull final AuthCredential credential) {

        return Completable.create(emitter ->
                firebase.signInWithCredential(credential)
                        .addOnSuccessListener(__ -> emitter.onComplete())
                        .addOnFailureListener(emitter::onError));
    }
}
