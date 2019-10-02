package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.common.Constant;
import com.kirinsidea.data.source.local.room.entity.Folder;

import io.reactivex.Single;

@Dao
public interface FolderDao extends BaseDao<Folder> {

    @Query(Constant.Query.selectFolderById)
    Single<Folder> selectById(final int id);
}
