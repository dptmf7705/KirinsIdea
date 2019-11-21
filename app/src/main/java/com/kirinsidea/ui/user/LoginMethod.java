package com.kirinsidea.ui.user;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public enum LoginMethod {
    GOOGLE("google"),
    KAKAO("kakao"),
    FACEBOOK("facebook");

    @NonNull
    private final String loginMethod;

    LoginMethod(@NonNull String loginMethod) {
        this.loginMethod = loginMethod;
    }

    @Nullable
    public static LoginMethod get(String type) {
        LoginMethod[] values = LoginMethod.values();
        for (LoginMethod value : values) {
            if (value.loginMethod.equals(type)) {
                return value;
            }
        }
        return null;
    }

    @NonNull
    public String getLoginMethod() {
        return loginMethod;
    }
}
