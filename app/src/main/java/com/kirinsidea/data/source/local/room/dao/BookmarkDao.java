package com.kirinsidea.data.source.local.room.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.data.source.local.room.entity.BookmarkEntity;

import io.reactivex.Single;

@Dao
public interface BookmarkDao extends BaseDao<BookmarkEntity> {

    /**
     * bookmark 테이블에서 id 기준으로 북마크 조회
     */
    @Query("SELECT * FROM bookmark WHERE id = :id LIMIT 1")
    Single<BookmarkEntity> selectById(final int id);

    /**
     * bookmark 테이블에서 전체 북마크 조회
     */
    @Query("SELECT * FROM bookmark")
    DataSource.Factory<Integer, BookmarkEntity> selectAll();

    /**
     * bookmark 테이블에서 originalWebUrl 기준으로 북마크 조회
     */
    @Query("SELECT COUNT(*) FROM bookmark WHERE originalWebUrl = :originalWebUrl")
    Single<Integer> selectByUrl(final String originalWebUrl);
}
