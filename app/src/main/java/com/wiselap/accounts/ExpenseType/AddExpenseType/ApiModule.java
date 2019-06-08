package com.wiselap.accounts.ExpenseType.AddExpenseType;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;

import dagger.Module;
import dagger.Provides;

@Module
public class ApiModule {

    @Provides
    static ServiceProvider<ApiInterface> providesService(){
        return new ServiceProvider<ApiInterface>() {
            @Override
            public ApiInterface getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(ApiInterface.class);
            }

            @Override
            public ApiInterface getWrappedService() {
                return RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
            }
        };
    }
}
