package com.wiselap.accounts.Select_Entity;

import com.wiselap.accounts.URLS;
import com.wiselap.accounts.model.Item;
import com.wiselap.accounts.retrofit.WrappedResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GetSelectEntity {

    @GET(URLS.getEntityTypes)
    Observable<WrappedResponse<List<Item>>> getEntityTypes();
}
