package com.kirinsidea.ui.login;

import android.content.Intent;

import androidx.annotation.NonNull;

import io.reactivex.Single;

public interface LoginNavigator {

    Single<Intent> navigateLoginWithGoogle(@NonNull final Intent intent);

    void navigateLoginWithEmail();

    void navigateLoginSuccess();
}
