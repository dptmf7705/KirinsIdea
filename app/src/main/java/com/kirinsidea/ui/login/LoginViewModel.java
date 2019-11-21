package com.kirinsidea.ui.login;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.login.LoginRepository;
import com.kirinsidea.data.source.remote.thirdparty.LoginHelper;
import com.kirinsidea.data.source.remote.thirdparty.facebook.FacebookLoginHelper;
import com.kirinsidea.data.source.remote.thirdparty.google.GoogleLoginHelper;
import com.kirinsidea.ui.base.BaseViewModel;
import com.kirinsidea.ui.user.LoginMethod;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class LoginViewModel extends BaseViewModel {
    private static final String TAG = "LoginViewModel";

    @NonNull
    private final MutableLiveData<LinkText[]> links = new MutableLiveData<>(LinkText.values());
    @NonNull
    private final MutableLiveData<LoginMethod> loginMethod = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<NewUser> newUser = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();

    private LoginHelper googleLoginHelper;
    private LoginHelper facebookLoginHelper;

    private LoginRepository repository;

    @NonNull
    @Override
    public BaseViewModel init(@NonNull final BaseRepository... repositories) {
        this.repository = (LoginRepository) repositories[0];
        return this;
    }

    void setGoogleLoginHelper(GoogleLoginHelper googleLoginHelper) {
        this.googleLoginHelper = googleLoginHelper;
    }

    void setFacebookLoginHelper(FacebookLoginHelper facebookLoginHelper) {
        this.facebookLoginHelper = facebookLoginHelper;
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (loginMethod.getValue() == LoginMethod.FACEBOOK) {
            facebookLoginHelper.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void signInWithGoogle() {
        this.loginMethod.setValue(LoginMethod.GOOGLE);
        startSignIn(googleLoginHelper);
    }

    public void signInWithFacebook() {
        this.loginMethod.setValue(LoginMethod.FACEBOOK);
        startSignIn(facebookLoginHelper);
    }

    private void startSignIn(@NonNull final LoginHelper helper) {
        addDisposable(helper.startSingIn()
                .observeOn(AndroidSchedulers.mainThread())
                .map(pair -> {
                    newUser.setValue(pair.first);
                    Log.e(TAG, "New User: " + pair.first.toString());
                    return pair.second;
                })
                .flatMapCompletable(repository::observeSignInFirebase)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    Log.e(TAG, "Login SUCCESS");
                    this.loginSuccess.setValue(true);
                }, e -> {
                    Log.e(TAG, "Login ERROR : " + e.getLocalizedMessage());
                    this.error.setValue(e);
                    e.printStackTrace();
                }));
    }

    @NonNull
    public LiveData<LinkText[]> getLinks() {
        return links;
    }

    @NonNull
    LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

}
