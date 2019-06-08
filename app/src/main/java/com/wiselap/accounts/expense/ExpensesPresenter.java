package com.wiselap.accounts.expense;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.users.UserReturnModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class ExpensesPresenter<V extends ExpensesContract.View> extends BasePresenterImpl<V> implements ExpensesContract.Presenter {
    ServiceProvider<ExpenseRequest> serviceProvider;
    @Inject
    public ExpensesPresenter(SchedulerProvider scheduler,ServiceProvider<ExpenseRequest> serviceProvider, CompositeDisposable disposable) {
        super(scheduler, disposable);
        this.serviceProvider=serviceProvider;
    }

    @Override
    public void getExpense(ExpenseMethodModel expenseMethodModel) {

        getDisposable().add(serviceProvider.getWrappedService().getExpense(1,expenseMethodModel)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<List<ExpenseReturnModel>>>() {
                    @Override
                    public void onNext(WrappedResponse<List<ExpenseReturnModel>> listWrappedResponse) {
                        getView().setExpense(listWrappedResponse.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getView().showMessage("Done");
                    }
                }));
    }
}
