package com.kirinsidea.data.source.local.room.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.kirinsidea.common.Constants;
import com.kirinsidea.data.source.local.room.entity.Memo;

import io.reactivex.Single;

@Dao
public interface MemoDao extends BaseDao<Memo> {

    @Query(Constants.Query.SELECT_ALL_MEMO_BY_BOOKMARK_ID)
    Single<Memo> selectByBookmarkId(final int bookmarkId);
}
