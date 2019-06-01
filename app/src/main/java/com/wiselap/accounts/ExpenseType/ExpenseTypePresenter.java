package com.wiselap.accounts.ExpenseType;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ExpenseTypePresenter<V extends ExpenseTypeContract.View> extends BasePresenterImpl<V> implements ExpenseTypeContract.Presenter {

    @Inject
    public ExpenseTypePresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }

    @Override
    public void setAdapter() {
        getView().createAdapter();
    }
}
