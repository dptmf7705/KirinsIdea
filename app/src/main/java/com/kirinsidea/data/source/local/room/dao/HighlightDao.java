package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.data.source.local.room.entity.HighlightEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface HighlightDao extends BaseDao<HighlightEntity> {

    @Query("SELECT * FROM highlight WHERE bookmarkId = :bookmarkId")
    Single<List<HighlightEntity>> selectAllByBookmarkId(int bookmarkId);
}
