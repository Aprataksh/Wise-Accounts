package com.wiselap.accounts.expense;

import com.wiselap.accounts.retrofit.WrappedResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ExpenseRequest {
    @POST("getExpense/{applicationuserid}")
    Observable<WrappedResponse<List<ExpenseReturnModel>>> getExpense(@Path("applicationuserid") long applicationuserid,
                                                                     @Body ExpenseMethodModel expenseMethodModel);
}
