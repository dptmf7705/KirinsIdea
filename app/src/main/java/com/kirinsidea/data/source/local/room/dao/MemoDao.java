package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.data.source.entity.MemoDetailEntity;
import com.kirinsidea.data.source.entity.MemoEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface MemoDao extends BaseDao<MemoEntity> {

    /**
     * memo, highlight 테이블에서 bookmarkId 기준으로 메모 + 하이라이트 리스트 조회
     */
    @Query("SELECT * FROM memo LEFT JOIN highlight USING (highlightId) WHERE bookmarkId = :bookmarkId")
    Single<List<MemoDetailEntity>> selectAllByBookmarkId(int bookmarkId);

    @Query("SELECT * FROM memo LEFT JOIN highlight USING (highlightId) WHERE bookmarkId = :bookmarkId AND highlightId = :highlightId")
    Single<MemoDetailEntity> selectByBookmarkIdAndHighlightId(int bookmarkId, int highlightId);
}
