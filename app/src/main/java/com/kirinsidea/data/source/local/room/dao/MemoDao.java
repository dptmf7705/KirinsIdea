package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.data.source.local.room.entity.MemoEntity;

import io.reactivex.Single;

@Dao
public interface MemoDao extends BaseDao<MemoEntity> {

    /**
     * memo 테이블에서 highlightId 기준으로 메모 조회
     */
    @Query("SELECT * FROM memo WHERE highlightId = :highlightId limit 1")
    Single<MemoEntity> selectByHighlightId(int highlightId);
}
