package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.local.room.entity.Folder;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FolderDao extends BaseDao<Folder> {

    @Query(Constants.Query.SELECT_FOLDER_BY_NAME)
    Single<Folder> selectByName(final String name);

    @Query(Constants.Query.SELECT_ALL_FOLDER)
    Single<List<Folder>> selectAll();
}
