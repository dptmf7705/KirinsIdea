package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.data.source.entity.ConfigEntity;

import io.reactivex.Maybe;

@Dao
public interface ConfigDao extends BaseDao<ConfigEntity> {

    @Query("SELECT * FROM config LIMIT 1")
    Maybe<ConfigEntity> selectDefaultConfig();

}
