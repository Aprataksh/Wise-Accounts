package com.wiselap.accounts.Select_Entity;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SelectEntityPresenter<V extends SelectEntityContract.SEView> extends BasePresenterImpl<V> implements SelectEntityContract.SEPresnter{

    @Inject
    public SelectEntityPresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }




    @Override
    public void setAdapter() {
        getView().createAdapter();
    }
}
