package com.wiselap.accounts.users;

import com.wiselap.accounts.retrofit.WrappedResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserRequest {

    @POST("accountingUsers")
    Observable<WrappedResponse<List<UserReturnModel>>> getUsers(@Body UserMethodModel userMethodModel);
}
