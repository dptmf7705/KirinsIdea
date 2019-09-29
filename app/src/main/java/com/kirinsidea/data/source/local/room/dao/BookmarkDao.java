package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.common.Constant;
import com.kirinsidea.data.source.local.room.entity.Bookmark;

import java.util.List;

import io.reactivex.Single;

@Dao
public abstract class BookmarkDao extends BaseDao<Bookmark> {

    @Query(Constant.Query.selectBookmarkById)
    public abstract Single<Bookmark> selectById(final int id);
}
