package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.data.source.entity.HighlightEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface HighlightDao extends BaseDao<HighlightEntity> {

    @Query("SELECT * FROM highlight WHERE bookmarkId = :bookmarkId")
    Single<List<HighlightEntity>> selectAllByBookmarkId(int bookmarkId);

    // TODO 서버 완료될 때까지 임시
    @Query("SELECT * FROM highlight WHERE bookmarkId = :bookmarkId AND startIndex = :startIndex AND endIndex = :endIndex")
    Single<HighlightEntity> selectByBookmarkId(int bookmarkId, int startIndex, int endIndex);
}
