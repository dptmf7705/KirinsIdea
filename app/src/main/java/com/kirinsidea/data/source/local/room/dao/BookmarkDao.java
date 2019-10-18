package com.kirinsidea.data.source.local.room.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.local.room.entity.BookmarkEntity;

import io.reactivex.Single;

@Dao
public interface BookmarkDao extends BaseDao<BookmarkEntity> {

    @Query(Constants.Query.SELECT_BOOKMARK_BY_ID)
    Single<BookmarkEntity> selectById(final int id);

    @Query(Constants.Query.SELECT_ALL_BOOKMARK)
    DataSource.Factory<Integer, BookmarkEntity> selectAll();

    @Query(Constants.Query.SELECT_BOOKMARK_BY_URL)
    Single<Integer> selectByUrl(final String originalWebUrl);
}
