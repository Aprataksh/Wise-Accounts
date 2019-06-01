package com.wiselap.accounts.Office;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class OfficePresenter<V extends OfficeContract.View> extends BasePresenterImpl<V> implements OfficeContract.Presenter {

    @Inject
    public OfficePresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }

    @Override
    public void sendData(String office, String contact, String owner, String address) {

    }
}
