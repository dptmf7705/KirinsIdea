package com.kirinsidea.data.source.remote.thirdparty.google;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.People;
import com.google.api.services.people.v1.model.Person;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kirinsidea.R;
import com.kirinsidea.data.source.remote.thirdparty.LoginHelper;
import com.kirinsidea.ui.login.NewUser;
import com.kirinsidea.ui.user.LoginMethod;
import com.kirinsidea.ui.user.UserGender;
import com.tedpark.tedonactivityresult.rx2.TedRxOnActivityResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class GoogleLoginHelper implements LoginHelper {
    private static final String TAG = "GoogleLoginHelper";

    private static final String ACCOUNT_TYPE_GOOGLE = "com.google";
    private static final String REQUEST_RESOURCE_NAME = "people/me";
    private static final String REQUEST_MASK_FIELDS = "person.birthdays,person.genders";

    @NonNull
    private final Activity activity;
    @NonNull
    private final GoogleSignInClient signInClient;

    private GoogleLoginHelper(@NonNull Activity activity,
                              @NonNull GoogleSignInClient signInClient) {
        this.activity = activity;
        this.signInClient = signInClient;
    }

    public static GoogleLoginHelper with(@NonNull Activity activity) {
        return new GoogleLoginHelper(activity, getGoogleSignInClient(activity));
    }

    @NonNull
    private static GoogleSignInClient getGoogleSignInClient(@NonNull Context context) {

        final GoogleSignInOptions options = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(com.kirinsidea.R.string.default_web_client_id))
                .requestProfile()
                .requestEmail()
                .build();

        return GoogleSignIn.getClient(context, options);
    }

    @NonNull
    @Override
    public Single<Pair<NewUser, AuthCredential>> startSingIn() {
        return TedRxOnActivityResult.with(activity)
                .startActivityForResult(signInClient.getSignInIntent())
                .flatMap(result -> getGoogleSignInAccount(result.getData()))
                .flatMap(account -> Single.zip(buildNewUser(account),
                        getFirebaseCredential(account.getIdToken()),
                        Pair::new));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // do nothing
    }

    /**
     * 파이어베이스 AuthCredential 객체 생성
     */
    @NonNull
    @Override
    public Single<AuthCredential> getFirebaseCredential(@Nullable String token) {
        return Maybe.just(GoogleAuthProvider.getCredential(token, null))
                .subscribeOn(Schedulers.io())
                .switchIfEmpty(Single.error(new Exception()));
    }

    /**
     * NewUser 객체 생성
     */
    @NonNull
    private Single<NewUser> buildNewUser(@NonNull final GoogleSignInAccount account) {

        /* GoogleSignInAccount 기본으로 제공하는 정보 */
        final NewUser.Builder builder = new NewUser.Builder()
                .setLoginMethod(LoginMethod.GOOGLE)
                .setName(account.getDisplayName())
                .setEmail(account.getEmail())
                .setProfileImageUrl(account.getPhotoUrl() == null ?
                        null : account.getPhotoUrl().toString());

        /* GooglePeople 부가 정보 */
        return buildGooglePerson(account.getEmail())
                .map(person -> {
                    if (person != null) {
                        // 성별 정보 추가
                        try {
                            builder.setGender(UserGender.valueOf(person.getGenders().get(0).getValue()));
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                        // 연령대 정보 추가
                        try {
                            builder.setAge(person.getBirthdays().get(0).getText());
                        } catch (NullPointerException | IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }
                    return builder.build();
                })
                .switchIfEmpty(Single.just(builder.build()))
                .subscribeOn(Schedulers.io());
    }

    /**
     * 부가 정보 추가 NewUser 객체 생성
     */
    @NonNull
    private Maybe<Person> buildGooglePerson(@Nullable final String email) {

        final GoogleAccountCredential credential = GoogleAccountCredential
                .usingOAuth2(activity, new ArrayList<>(Collections.singletonList(Scopes.PROFILE)))
                .setSelectedAccount(new Account(email, ACCOUNT_TYPE_GOOGLE));

        Maybe<Person> stream = Maybe.create(emitter -> {
            try {
                emitter.onSuccess(new People
                        .Builder(new NetHttpTransport(), new JacksonFactory(), credential)
                        .setApplicationName(activity.getString(R.string.app_name))
                        .build().people()
                        .get(REQUEST_RESOURCE_NAME)
                        .setRequestMaskIncludeField(REQUEST_MASK_FIELDS)
                        .execute());
                emitter.onComplete();
            } catch (IOException e) {
                e.printStackTrace();
                emitter.onComplete();
            }
        });

        return stream.subscribeOn(Schedulers.io());
    }

    /**
     * 구글 로그인 이후 GoogleSignInAccount 객체 생성
     */
    @NonNull
    private Single<GoogleSignInAccount> getGoogleSignInAccount(@NonNull final Intent intent) {
        final Maybe<GoogleSignInAccount> stream = Maybe.create(
                emitter -> GoogleSignIn.getSignedInAccountFromIntent(intent)
                        .addOnSuccessListener(emitter::onSuccess)
                        .addOnFailureListener(emitter::onError));

        return stream.subscribeOn(Schedulers.io())
                .switchIfEmpty(Single.error(new Exception()));
    }
}
