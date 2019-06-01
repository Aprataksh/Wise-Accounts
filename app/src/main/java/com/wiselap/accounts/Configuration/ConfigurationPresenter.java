package com.wiselap.accounts.Configuration;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ConfigurationPresenter<V extends ConfigurationContract.View> extends BasePresenterImpl<V> implements ConfigurationContract.Presenter {

    @Inject
    public ConfigurationPresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }

    @Override
    public void setAdapter() {
        getView().createAdapter();
    }
}
