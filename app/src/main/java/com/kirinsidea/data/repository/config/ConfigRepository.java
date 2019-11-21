package com.kirinsidea.data.repository.config;

import androidx.annotation.NonNull;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.source.entity.ConfigEntity;

import io.reactivex.Single;

public interface ConfigRepository extends BaseRepository {

    @NonNull
    Single<ConfigEntity> observeDefaultConfig();

    @NonNull
    Single<ConfigEntity> observeNewConfig();

    @NonNull
    Single<ConfigEntity> observeUpdateConfig(@NonNull ConfigEntity entity);

}
