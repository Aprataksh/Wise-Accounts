package com.wiselap.accounts.Report;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ReportPresenter<V extends ReportContract.View> extends BasePresenterImpl<V> implements ReportContract.Presenter {

    @Inject
    public ReportPresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }

    @Override
    public void setAdapter() {
        getView().createAdapter();
    }
}
