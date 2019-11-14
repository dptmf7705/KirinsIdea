package com.kirinsidea.ui.user;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.kirinsidea.App;
import com.kirinsidea.data.repository.BaseRepository;
import com.kirinsidea.data.repository.UserRepository;
import com.kirinsidea.data.source.local.room.error.RoomException;
import com.kirinsidea.data.source.local.room.error.RoomResult;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitResultCode;
import com.kirinsidea.data.source.remote.kirin.error.RetrofitException;
import com.kirinsidea.ui.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class UserViewModel extends BaseViewModel {
    private UserRepository repository;

    /**
     * 새 메모 추가
     */
    private void addNewUser(@NonNull final String name) {
        addDisposable(repository.observeAddNewUser(name)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> {
                    if(error instanceof RetrofitException){ // 서버 통신 에러
                        RetrofitResultCode code = ((RetrofitException) error).getRetrofitResultCode();
                        if(code == RetrofitResultCode.LOGIN_ERROR_405){ // 이미 가입된 계정
                            Toast.makeText(App.instance().getContext(), code.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else if(error instanceof RoomException){ // 룸 에러
                        RoomResult result = ((RoomException) error).getRoomResult();
                        if(result == RoomResult.ALREADY_EXIST){ // 이미 존재
                            Toast.makeText(App.instance().getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .subscribe(entity -> {

                }));
    }

    @NonNull
    @Override
    public BaseViewModel init(@NonNull BaseRepository... repositories) {
        return null;
    }
}
