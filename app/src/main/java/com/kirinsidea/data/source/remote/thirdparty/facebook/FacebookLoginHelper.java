package com.kirinsidea.data.source.remote.thirdparty.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.kirinsidea.data.source.remote.thirdparty.LoginHelper;
import com.kirinsidea.ui.login.NewUser;
import com.kirinsidea.ui.user.UserGender;

import java.lang.ref.WeakReference;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class FacebookLoginHelper implements LoginHelper {
    private static final String TAG = "FacebookLoginHelper";

    private static final String REQUEST_FIELD_PROFILE = "public_profile";
    private static final String REQUEST_FIELD_EMAIL = "email";
    private static final String REQUEST_FIELD_GENDER = "gender";
    private static final String REQUEST_FIELD_BIRTHDAY = "birthday";

    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_GENDER = "gender";
    private static final String FIELD_BIRTHDAY = "birthday";

    private static final String REQUEST_KEY_FIELDS = "fields";
    private static final String REQUEST_VALUE_FIELDS = "email,gender,birthday";

    @NonNull
    private final WeakReference<LoginButton> loginButton;
    @NonNull
    private final CallbackManager callbackManager;

    private FacebookLoginHelper(@NonNull CallbackManager callbackManager,
                                @NonNull WeakReference<LoginButton> loginButton) {
        this.callbackManager = callbackManager;
        this.loginButton = loginButton;
    }

    public static FacebookLoginHelper with(@NonNull LoginButton loginButton) {
        return new FacebookLoginHelper(CallbackManager.Factory.create(),
                new WeakReference<>(loginButton));
    }

    @NonNull
    @Override
    public Single<Pair<NewUser, AuthCredential>> startSingIn() {
        loginButton.get().setPermissions(
                REQUEST_FIELD_PROFILE,
                REQUEST_FIELD_EMAIL,
                REQUEST_FIELD_GENDER,
                REQUEST_FIELD_BIRTHDAY);

        final Single<LoginResult> stream = Single.create(emitter ->
                loginButton.get().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        emitter.onSuccess(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        emitter.onError(new Exception());
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        emitter.onError(error);
                    }
                }));

        return stream.flatMap(result -> Single
                .zip(buildNewUser(Profile.getCurrentProfile(), result.getAccessToken()),
                        getFirebaseCredential(result.getAccessToken().getToken()),
                        Pair::new))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @NonNull
    @Override
    public Single<AuthCredential> getFirebaseCredential(@Nullable String token) {
        if (token == null) {
            return Single.error(new Exception());
        }

        return Maybe.just(FacebookAuthProvider.getCredential(token))
                .switchIfEmpty(Single.error(new Exception()));
    }

    /**
     * NewUser 객체 생성
     */
    @NonNull
    private Single<NewUser> buildNewUser(@NonNull final Profile profile,
                                         @NonNull final AccessToken token) {

        /* Facebook Profile 기본으로 제공하는 정보 */
        final NewUser.Builder builder = new NewUser.Builder()
                .setName(profile.getName())
                .setProfileImageUrl(profile.getProfilePictureUri(100, 100)
                        .toString());

        final Single<NewUser> stream = Single.create(emitter -> {

            GraphRequest request = GraphRequest.newMeRequest(token,
                    (me, response) -> {
                        /* Facebook Graph 부가 정보 */
                        if (response.getError() == null) {
                            builder.setEmail(me.optString(FIELD_EMAIL))
                                    .setGender(UserGender.valueOf(me.optString(FIELD_GENDER)))
                                    .setAge(me.optString(FIELD_BIRTHDAY));
                        }
                        emitter.onSuccess(builder.build());
                    });

            Bundle bundle = new Bundle();
            bundle.putString(REQUEST_KEY_FIELDS, REQUEST_VALUE_FIELDS);

            request.setParameters(bundle);
            request.executeAsync();
        });

        return stream.subscribeOn(Schedulers.io());
    }
}
