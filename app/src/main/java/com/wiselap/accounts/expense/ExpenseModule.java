package com.wiselap.accounts.expense;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;

import dagger.Module;
import dagger.Provides;

@Module
public class ExpenseModule {
    @Provides
    static ServiceProvider<ExpenseRequest> provideSerivce()
    {
        return new ServiceProvider<ExpenseRequest>() {
            @Override
            public ExpenseRequest getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(ExpenseRequest.class);
            }

            @Override
            public ExpenseRequest getWrappedService() {
                return RetrofitInstance.getRetrofitInstance().create(ExpenseRequest.class);
            }
        };
    }
}
