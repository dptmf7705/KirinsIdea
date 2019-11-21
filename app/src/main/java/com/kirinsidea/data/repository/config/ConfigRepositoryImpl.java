package com.kirinsidea.data.repository.config;

import androidx.annotation.NonNull;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.source.entity.ConfigEntity;
import com.kirinsidea.data.source.local.room.dao.ConfigDao;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ConfigRepositoryImpl implements ConfigRepository {
    private static class LazyHolder {
        private static final ConfigRepository INSTANCE = new ConfigRepositoryImpl();
    }

    @NonNull
    public static ConfigRepository getInstance() {
        return ConfigRepositoryImpl.LazyHolder.INSTANCE;
    }

    private ConfigRepositoryImpl() {
    }

    private ConfigDao configLocalDataSource;

    @NonNull
    @Override
    public BaseRepository init(@NonNull Object... dataSources) {
        this.configLocalDataSource = (ConfigDao) dataSources[0];
        return this;
    }

    @NonNull
    @Override
    public Single<ConfigEntity> observeDefaultConfig() {
        return configLocalDataSource
                .selectDefaultConfig()
                .switchIfEmpty(observeNewConfig())
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<ConfigEntity> observeNewConfig() {
        final ConfigEntity entity = new ConfigEntity(ConfigEntity.DEFAULT_ID);
        return configLocalDataSource
                .insert(entity)
                .subscribeOn(Schedulers.io())
                .andThen(Single.just(entity));
    }

    @NonNull
    @Override
    public Single<ConfigEntity> observeUpdateConfig(@NonNull ConfigEntity entity) {
        return configLocalDataSource
                .update(entity)
                .subscribeOn(Schedulers.io())
                .andThen(observeDefaultConfig());
    }
}
