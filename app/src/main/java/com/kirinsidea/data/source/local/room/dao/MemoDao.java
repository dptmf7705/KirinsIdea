package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.local.room.entity.MemoEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface MemoDao extends BaseDao<MemoEntity> {

    @Query(Constants.Query.SELECT_ALL_MEMO_BY_BOOKMARK_ID)
    Single<List<MemoEntity>> selectByBookmarkId(final int bookmarkId);
}
