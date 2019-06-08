package com.wiselap.accounts.Select_Entity;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class SelectEntityModule {

    @Provides
    static ServiceProvider<GetSelectEntity> provideService()
    {
        return new ServiceProvider<GetSelectEntity>() {
            @Override
            public GetSelectEntity getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(GetSelectEntity.class);
            }

            @Override
            public GetSelectEntity getWrappedService() {
                return RetrofitInstance.getRetrofitInstance().create(GetSelectEntity.class);
            }

        };
    }
}
