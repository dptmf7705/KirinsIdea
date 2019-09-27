package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

import com.kirinsidea.data.source.local.room.entity.Bookmark;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Completable insert(final T entity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Completable insertAll(final List<T> entities);

    @SuppressWarnings("unchecked")
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Completable insertAll(final T... entities);

    @RawQuery
    abstract Single<List<Bookmark>> selectAll(SupportSQLiteQuery query);

    public Single<List<Bookmark>> selectAll() {
        return selectAll(new SimpleSQLiteQuery("SELECT * FROM " + getTableName()));
    }

    /* TODO. 코드수정 -> getTableName() 오버라이딩을 강제할 수 없음 */
    protected abstract String getTableName();

    @Update
    public abstract Completable update(final T entity);

    @Update
    public abstract Completable updateAll(final List<T> entities);

    @SuppressWarnings("unchecked")
    @Update
    public abstract Completable updateAll(final T... entities);

    @Delete
    public abstract Completable delete(final T entity);

    @Delete
    public abstract Completable deleteAll(final List<T> entities);

    @SuppressWarnings("unchecked")
    @Delete
    public abstract Completable deleteAll(final T... entities);
}