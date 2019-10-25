package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.entity.MemoEntity;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface MemoRepository extends BaseRepository {

    @NonNull
    Single<MemoEntity> observeMemoByHighlightId(final int highlightId);

    @NonNull
    Completable observeAddNewMemo(@NonNull final MemoEntity memo);

    @NonNull
    Completable observeUpdateMemo(@NonNull final MemoEntity memo);

    @NonNull
    Completable observeDeleteMemo(@NonNull final MemoEntity memo);
}
