package com.wiselap.accounts.ExpenseType;

import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.RetrofitInstance;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ExpenseTypeModule {

    @Provides
    static ServiceProvider<GetExpenseTypes> providesExpenseService(){
        return new ServiceProvider<GetExpenseTypes>() {
            @Override
            public GetExpenseTypes getService() {
                return RetrofitInstance.getCustomRetrofitInstance().create(GetExpenseTypes.class);
            }

            @Override
            public GetExpenseTypes getWrappedService() {
                return  RetrofitInstance.getRetrofitInstance().create(GetExpenseTypes.class);
            }
        };
    }
}
