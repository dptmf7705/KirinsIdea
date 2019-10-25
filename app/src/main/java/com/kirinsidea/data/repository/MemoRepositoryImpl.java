package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.dao.MemoDao;
import com.kirinsidea.data.source.local.room.entity.MemoEntity;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MemoRepositoryImpl implements MemoRepository {
    private static class LazyHolder {
        private static final MemoRepository INSTANCE = new MemoRepositoryImpl();
    }

    public static MemoRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private MemoRepositoryImpl() {
    }

    private MemoDao memoLocalDataSource;

    @NonNull
    @Override
    public BaseRepository init(@NonNull Object... dataSources) {
        this.memoLocalDataSource = (MemoDao) dataSources[0];
        return this;
    }

    @NonNull
    @Override
    public Single<MemoEntity> observeMemoByHighlightId(int highlightId) {
        return memoLocalDataSource.selectByHighlightId(highlightId)
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Completable observeAddNewMemo(@NonNull MemoEntity memo) {
        return memoLocalDataSource.insert(memo).subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Completable observeUpdateMemo(@NonNull MemoEntity memo) {
        return memoLocalDataSource.update(memo).subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Completable observeDeleteMemo(@NonNull MemoEntity memo) {
        return memoLocalDataSource.delete(memo).subscribeOn(Schedulers.io());
    }
}
