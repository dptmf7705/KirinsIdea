package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;

public interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(final T entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(final List<T> entities);

    @SuppressWarnings("unchecked")
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(final T... entities);

    @Update
    Completable update(final T entity);

    @Update
    Completable updateAll(final List<T> entities);

    @SuppressWarnings("unchecked")
    @Update
    Completable updateAll(final T... entities);

    @Delete
    Completable delete(final T entity);

    @Delete
    Completable deleteAll(final List<T> entities);

    @SuppressWarnings("unchecked")
    @Delete
    Completable deleteAll(final T... entities);
}