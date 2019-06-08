package com.wiselap.accounts.users;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;

import dagger.Module;
import dagger.Provides;
@Module
public class UserModule {

    @Provides
    static ServiceProvider<UserRequest> provideSerivce()
    {
        return new ServiceProvider<UserRequest>() {
            @Override
            public UserRequest getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(UserRequest.class);
            }

            @Override
            public UserRequest getWrappedService() {
                return RetrofitInstance.getRetrofitInstance().create(UserRequest.class);
            }
        };
    }
}
