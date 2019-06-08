package com.wiselap.accounts.ExpenseType;

import com.wiselap.accounts.URLS;
import com.wiselap.accounts.model.ConfExpenseType;
import com.wiselap.accounts.model.ExpenseType;
import com.wiselap.accounts.retrofit.WrappedResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface GetExpenseTypes {

    @GET(URLS.getExpenseTypes+"/{applicationUserId}")
    Observable<WrappedResponse<List<ExpenseType>>> getExpenseTypes(@Path("applicationUserId") long applicationUserId);

    @HTTP(method = "DELETE", path = URLS.deleteExpenseTypes, hasBody = true)
    Observable<WrappedResponse> deleteExpenseTypes(@Body ConfExpenseType confExpenseType);
}
