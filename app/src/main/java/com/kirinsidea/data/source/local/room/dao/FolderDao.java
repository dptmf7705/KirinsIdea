package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.data.source.local.room.entity.FolderEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FolderDao extends BaseDao<FolderEntity> {

    /**
     * folder 테이블에서 id 기준으로 북마크 조회
     */
    @Query("SELECT * FROM folder WHERE id = :id LIMIT 1")
    Single<FolderEntity> selectById(final int id);

    /**
     * folder 테이블에서 favorite, storeTime 기준으로 북마크 조회
     */

    @Query("SELECT * FROM folder ORDER BY isFavorite DESC, storeTime DESC")
    Single<List<FolderEntity>> selectFolderList();
}
