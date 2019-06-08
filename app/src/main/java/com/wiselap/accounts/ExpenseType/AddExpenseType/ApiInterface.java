package com.wiselap.accounts.ExpenseType.AddExpenseType;

import com.wiselap.accounts.URLS;
import com.wiselap.accounts.model.ConfExpenseType;
import com.wiselap.accounts.model.ExpenseType;
import com.wiselap.accounts.retrofit.WrappedResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {


    @POST(URLS.getConfigurationExpenseTypes)
    Observable<WrappedResponse<ExpenseType>> getConfigurationExpenseTypes(@Body ConfExpenseType confExpenseType);

    @POST(URLS.addConfigurationExpenseTypes)
    Observable<WrappedResponse<ExpenseType>> addConfigurationExpenseTypes(@Body ExpenseType expenseType);

    @PUT(URLS.updateConfigurationExpenseTypes)
    Observable<WrappedResponse<ExpenseType>> updateConfigurationExpenseTypes(@Body ExpenseType expenseType);
}
