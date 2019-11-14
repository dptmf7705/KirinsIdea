package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.entity.UserEntity;

import io.reactivex.Single;

public interface UserRepository {

    @NonNull
    Single<UserEntity> observeUser(final String userId);

    @NonNull
    Single<UserEntity> observeAddNewUser(@NonNull final String user);
}
