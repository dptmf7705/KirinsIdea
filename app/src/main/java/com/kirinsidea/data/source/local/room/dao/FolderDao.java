package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.data.source.entity.FolderEntity;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface FolderDao extends BaseDao<FolderEntity> {

    /**
     * FolderEntity 테이블에서 id 기준으로 북마크 조회
     */
    @Query("SELECT * FROM FolderEntity WHERE id = :id LIMIT 1")
    Maybe<FolderEntity> selectById(final String id);

    /**
     * FolderEntity 테이블에서 favorite, storeTime 기준으로 북마크 조회
     */

    @Query("SELECT * FROM FolderEntity ORDER BY isFavorite DESC, storeTime DESC")
    Single<List<FolderEntity>> selectFolderList();

    /**
     * FolderEntity 테이블에서 Favorite 인 폴더로 북마크 조회/ 앱 시작시 북마크 페이지
     */
    @Query("SELECT id FROM FolderEntity WHERE isFavorite")
    Maybe<String> selectedByFavorite();
}
