package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.common.Constant;
import com.kirinsidea.data.source.local.room.entity.Folder;

import io.reactivex.Single;

@Dao
public interface FolderDao extends BaseDao<Folder> {

    @Query(Constant.Query.SELECT_FOLDER_BY_NAME)
    Single<Folder> selectByName(final String name);

    @Query(Constant.Query.SELECT_FOLDER_ALL)
    Single<Folder> selectAll();
}
