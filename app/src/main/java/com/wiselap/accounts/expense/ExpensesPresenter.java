package com.wiselap.accounts.expense;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ExpensesPresenter<V extends ExpensesContract.View> extends BasePresenterImpl<V> implements ExpensesContract.Presenter {

    @Inject
    public ExpensesPresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }

    @Override
    public void setAdapter() {
        getView().createAdapter();
    }
}
