package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.data.source.entity.UserEntity;

import io.reactivex.Maybe;

@Dao
public interface UserDao extends BaseDao<UserEntity> {

    @Query("SELECT * FROM user WHERE userId = :id LIMIT 1")
    Maybe<UserEntity> selectById(final String id);

}
