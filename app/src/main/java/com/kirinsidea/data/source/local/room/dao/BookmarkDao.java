package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;

import com.kirinsidea.data.source.local.room.entity.Bookmark;

@Dao
public abstract class BookmarkDao extends BaseDao<Bookmark> {

    @Override
    protected String getTableName() {
        return "Bookmark";
    }
}
