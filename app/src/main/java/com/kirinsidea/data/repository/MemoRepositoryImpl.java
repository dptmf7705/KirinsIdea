package com.kirinsidea.data.repository;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.local.room.dao.MemoDao;
import com.kirinsidea.data.source.local.room.entity.Memo;
import com.kirinsidea.data.source.remote.kirin.RetrofitClient;
import com.kirinsidea.data.source.remote.kirin.mapper.MemoRequestMapper;
import com.kirinsidea.data.source.remote.kirin.request.NewMemoRequest;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MemoRepositoryImpl implements MemoRepository {
    private static class LazyHolder {
        private static final MemoRepository INSTANCE = new MemoRepositoryImpl();
    }

    @NonNull
    public static MemoRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private MemoRepositoryImpl() {
    }

    private RetrofitClient retrofit;
    private MemoDao memoDao;

    @NonNull
    @Override
    public BaseRepository init(@NonNull Object... dataSources) {
        this.retrofit = (RetrofitClient) dataSources[0];
        this.memoDao = (MemoDao) dataSources[1];
        return this;
    }

    @NonNull
    @Override
    public Completable observeAddNewMemo(@NonNull final NewMemoRequest request) {
//        return Completable
//                .mergeArray(retrofit.observeAddNewMemo(request),
//                        memoDao.insert(MemoRequestMapper.toMemo(request)))
//                .subscribeOn(Schedulers.io());
        return memoDao.insert(MemoRequestMapper.toMemo(request))
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Single<List<Memo>> observeMemoList(final int bookmarkId) {
        return memoDao.selectByBookmarkId(bookmarkId)
                .subscribeOn(Schedulers.io());
    }

    @NonNull
    @Override
    public Completable observeDeleteMemo(Memo memo) {
        return memoDao.delete(memo).subscribeOn(Schedulers.io());
    }
}
