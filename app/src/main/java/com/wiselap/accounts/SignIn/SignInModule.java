package com.wiselap.accounts.SignIn;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;

import dagger.Module;
import dagger.Provides;

@Module
public class SignInModule {
    @Provides
    static ServiceProvider<EmailRequest> provideSerivce()
    {
        return new ServiceProvider<EmailRequest>() {
            @Override
            public EmailRequest getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(EmailRequest.class);
            }

            @Override
            public EmailRequest getWrappedService() {
                return RetrofitInstance.getRetrofitInstance().create(EmailRequest.class);
            }
        };
    }
}
