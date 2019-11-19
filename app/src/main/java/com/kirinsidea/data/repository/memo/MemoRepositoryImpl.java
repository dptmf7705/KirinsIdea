package com.kirinsidea.data.repository.memo;

import androidx.annotation.NonNull;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.source.local.room.dao.MemoDao;
import com.kirinsidea.data.source.entity.MemoDetailEntity;
import com.kirinsidea.data.source.entity.MemoEntity;
import com.kirinsidea.ui.memo.Memo;
import com.kirinsidea.ui.memo.NewMemo;

import java.util.ArrayList;
import java.util.List;

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
    public Single<List<Memo>> observeMemoListByBookmarkId(String bookmarkId) {
        return memoLocalDataSource.selectAllByBookmarkId(bookmarkId)
                .map(list -> {
                    List<Memo> resultList = new ArrayList<>();
                    for (MemoDetailEntity entity : list) {
                        resultList.add(Memo.Builder.with(entity).build());
                    }
                    return resultList;
                })
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<Memo> observeAddNewMemo(@NonNull NewMemo memo) {
        return memoLocalDataSource.insert(MemoEntity.Builder.with(memo).build())
                .toSingleDefault(true)
                .flatMap(__ -> observeMemo(memo.getBookmarkId(), memo.getHighlightId()))
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<Memo> observeUpdateMemo(@NonNull Memo memo) {
        return memoLocalDataSource.update(MemoEntity.Builder.with(memo).build())
                .toSingleDefault(true)
                .flatMap(__ -> observeMemo(memo.getBookmarkId(), memo.getHighlightId()))
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Completable observeDeleteMemo(@NonNull Memo memo) {
        return memoLocalDataSource.delete(MemoEntity.Builder.with(memo).build())
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<Memo> observeMemo(String bookmarkId, String highlightId) {
        return memoLocalDataSource.selectByBookmarkIdAndHighlightId(bookmarkId, highlightId)
                .map(entity -> Memo.Builder.with(entity).build())
                .subscribeOn(Schedulers.io());
    }
}
