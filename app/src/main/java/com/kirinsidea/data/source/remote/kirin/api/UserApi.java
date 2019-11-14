package com.kirinsidea.data.source.remote.kirin.api;

import com.kirinsidea.data.source.entity.UserEntity;
import com.kirinsidea.data.source.remote.kirin.response.SingleResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {

    @POST("folder")
    Single<SingleResponse<UserEntity>> addNewUser(@Body String name);

}
