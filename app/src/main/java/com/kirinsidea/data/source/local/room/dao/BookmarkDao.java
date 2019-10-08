package com.kirinsidea.data.source.local.room.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.local.room.entity.Bookmark;

import io.reactivex.Single;

@Dao
public interface BookmarkDao extends BaseDao<Bookmark> {

    @Query(Constants.Query.SELECT_BOOKMARK_BY_ID)
    Single<Bookmark> selectById(final int id);

    @Query(Constants.Query.SELECT_ALL_BOOKMARK)
    DataSource.Factory<Integer, Bookmark> selectAll();
}
