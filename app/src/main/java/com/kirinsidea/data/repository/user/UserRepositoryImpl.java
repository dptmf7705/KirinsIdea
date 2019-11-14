package com.kirinsidea.data.repository.user;

import androidx.annotation.NonNull;

import com.kirinsidea.data.source.entity.UserEntity;
import com.kirinsidea.data.source.local.room.dao.UserDao;
import com.kirinsidea.data.source.local.room.error.RoomException;
import com.kirinsidea.data.source.local.room.error.RoomResult;
import com.kirinsidea.data.source.remote.kirin.api.UserApi;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitResultCode;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitException;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UserRepositoryImpl implements UserRepository {

    private UserApi userApi;
    private UserDao userDao;

    @NonNull
    @Override
    public Single<UserEntity> observeUser(String userId) {
        return null;
    }

    @NonNull
    @Override
    public Single<UserEntity> observeAddNewUser(@NonNull String name) {
        return userApi.addNewUser(name)
                .subscribeOn(Schedulers.io())
                .flatMap(response -> {
                    if(response.getRetrofitResultCode() == RetrofitResultCode.SUCCESS){ // 성공
                        return userDao.insert(response.getResult())
                                .subscribeOn(Schedulers.io())
                                .andThen(userDao.selectById(response.getResult().getUserId()))
                                .switchIfEmpty(Single.error(new RoomException(RoomResult.NULL)));
                    } else { // 실패
                        return Single.error(new RetrofitException(response.getRetrofitResultCode()));
                    }
                });
    }
}
