package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.ui.memo.Memo;
import com.kirinsidea.ui.memo.NewMemo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface MemoRepository extends BaseRepository {

    @NonNull
    Single<List<Memo>> observeMemoListByBookmarkId(final int bookmarkId);

    @NonNull
    Single<Memo> observeUpdateMemo(@NonNull final Memo memo);

    @NonNull
    Completable observeDeleteMemo(@NonNull final Memo memo);

    // TODO 서버 완료될 때까지 임시
    @NonNull
    Single<Memo> observeMemo(final int bookmarkId, final int highlightId);

    @NonNull
    Single<Memo> observeAddNewMemo(@NonNull final NewMemo memo);

}
