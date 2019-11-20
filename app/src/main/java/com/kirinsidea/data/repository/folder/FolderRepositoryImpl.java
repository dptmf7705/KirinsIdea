package com.kirinsidea.data.repository.folder;

import androidx.annotation.NonNull;

import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.source.entity.FolderEntity;
import com.kirinsidea.data.source.local.room.dao.FolderDao;
import com.kirinsidea.data.source.local.room.error.RoomException;
import com.kirinsidea.data.source.local.room.error.RoomResult;
import com.kirinsidea.data.source.remote.kirin.api.FolderApi;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitException;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitResultCode;
import com.kirinsidea.data.source.remote.kirin.mock.FolderMockApi;
import com.kirinsidea.data.source.remote.kirin.request.ChangeFolderRequest;
import com.kirinsidea.data.source.remote.kirin.request.NewFolderRequest;
import com.kirinsidea.ui.folderlist.Folder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class FolderRepositoryImpl implements FolderRepository {
    private static class LazyHolder {
        private static final FolderRepository INSTANCE = new FolderRepositoryImpl();
    }

    @NonNull
    public static FolderRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private FolderRepositoryImpl() {
    }

    private FolderDao folderLocalDataSource;
    private FolderApi folderRemoteDataSource;

    @NonNull
    @Override
    public BaseRepository init(@NonNull Object... dataSources) {
        this.folderLocalDataSource = (FolderDao) dataSources[0];
//        this.folderRemoteDataSource = (FolderApi) dataSources[1];
        this.folderRemoteDataSource = new FolderMockApi();
        return this;
    }

    //TODO 아직까지는 사용 안함
    @NonNull
    @Override
    public Maybe<Folder> observeFolderById(String folderId) {
        return folderLocalDataSource.selectById(folderId).subscribeOn(Schedulers.io())
                .map(entity -> {
                    Folder folder = new Folder.Builder(entity).build();
                    return folder;
                })
                .switchIfEmpty(Maybe.error(new RoomException(RoomResult.NULL))); //없는 경우 NULL 에러
    }

    @NonNull
    @Override
    public Single<List<Folder>> observeFolderList() {
        return folderLocalDataSource.selectFolderList().subscribeOn(Schedulers.io())
                .map(list -> {
                    List<Folder> folderList = new ArrayList<>();
                    for (FolderEntity entity : list) {
                        folderList.add(new Folder.Builder(entity).build());
                    }
                    return folderList;
                });
    }

    @NonNull
    @Override
    public Single<String> observeAddNewFolder(@NonNull NewFolderRequest request) {
        return folderRemoteDataSource.addNewFolder(request)
                .subscribeOn(Schedulers.io())
                .flatMap(response -> {
                            if (response.getRetrofitResultCode() == RetrofitResultCode.SUCCESS) {
                                return folderLocalDataSource.insert(response.getResult()).
                                        toSingleDefault(response.getResult().getId()).subscribeOn(Schedulers.io());
                            }else{
                                return Single.error(new RetrofitException(response.getRetrofitResultCode()));
                            }
                        }
                );
    }

    /**
     * 핀 폴더 변경 시 folderEntity 업데이트
     */
    @NonNull
    @Override
    public Completable observeChangeFavorite(@NonNull Folder folder) {
        return folderLocalDataSource.update(FolderEntity.Builder.with(folder).build()).subscribeOn(Schedulers.io());
    }


    // TODO 수정 변경 시간 받는지 / 에러처리
    /**
     * 폴더 이름 수정
     */
    @NonNull
    @Override
    public Single<String> observeChangeFolderName(@NonNull ChangeFolderRequest request
            , @NonNull Folder folder) {
        FolderEntity entity = new FolderEntity(folder.getId(), folder.getName());
        return folderRemoteDataSource.changeFolderName(request)
                .andThen(folderLocalDataSource.update(entity).toSingleDefault(folder.getId()))
                .subscribeOn(Schedulers.io());
    }

    /**
     * 핀 폴더 조회 후 없으면 defaultValue 전체
     */
    @Override
    public Single<String> observeBookmarkByFavorite() {
        return folderLocalDataSource.selectedByFavorite().subscribeOn(Schedulers.io()).toSingle(ALL_BOOKMARK);
    }
}
