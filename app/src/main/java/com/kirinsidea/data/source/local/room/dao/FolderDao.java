package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.data.source.local.room.entity.FolderEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FolderDao extends BaseDao<FolderEntity> {

    /**
     * folder 테이블에서 name 기준으로 폴더 조회
     */
    @Query("SELECT * FROM folder WHERE name = :name LIMIT 1")
    Single<FolderEntity> selectByName(final String name);

    /**
     * folder 테이블에서 전체 폴더 조회
     */
    @Query("SELECT * FROM folder")
    Single<List<FolderEntity>> selectAll();
}
