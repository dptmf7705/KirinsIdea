package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.Memo;
import com.kirinsidea.data.source.remote.kirin.request.NewMemoRequest;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface MemoRepository extends BaseRepository {

    @NonNull
    Completable observeAddNewMemo(@NonNull final NewMemoRequest request);

    @NonNull
    Single<List<Memo>> observeMemoList(final int bookmarkId);

    @NonNull
    Completable observeDeleteMemo(final Memo memo);
}
