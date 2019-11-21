package com.kirinsidea.ui.user;

import androidx.annotation.NonNull;

public enum UserGender {
    MALE("male"),
    FEMALE("female"),
    male("male"),
    female("female"),
    Male("male"),
    Female("female");

    @NonNull
    private final String gender;

    UserGender(@NonNull String gender) {
        this.gender = gender;
    }

    @NonNull
    public String getGender() {
        return gender;
    }
}
