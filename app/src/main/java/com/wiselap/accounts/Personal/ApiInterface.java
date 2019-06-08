package com.wiselap.accounts.Personal;

import com.wiselap.accounts.URLS;
import com.wiselap.accounts.model.EntityModel;
import com.wiselap.accounts.model.OfficeEntity;
import com.wiselap.accounts.model.PersonalEntity;
import com.wiselap.accounts.retrofit.WrappedResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST(URLS.addAccountingProfile)
    Observable<WrappedResponse<EntityModel>> sendPersonalEntity(@Body PersonalEntity personalEntity);

    @POST(URLS.addAccountingProfile)
    Observable<WrappedResponse<EntityModel>> sendOfficeEntity(@Body OfficeEntity officeEntity);
}
