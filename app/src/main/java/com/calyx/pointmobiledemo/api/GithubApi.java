package com.calyx.pointmobiledemo.api;

import com.calyx.pointmobiledemo.api.model.UserResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GithubApi {

    @GET
    Observable<UserResponse> getUserList(@Url String url);
}
