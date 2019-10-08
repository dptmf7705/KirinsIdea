package com.kirinsidea.data.source.local.room.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.common.Constant;
import com.kirinsidea.data.source.local.room.entity.Bookmark;

import io.reactivex.Single;

@Dao
public interface BookmarkDao extends BaseDao<Bookmark> {

    @Query(Constant.Query.SELECT_BOOKMARK_BY_ID)
    Single<Bookmark> selectById(final int id);

    @Query(Constant.Query.SELECT_BOOKMARK_ALL)
    DataSource.Factory<Integer, Bookmark> selectAll();
}
