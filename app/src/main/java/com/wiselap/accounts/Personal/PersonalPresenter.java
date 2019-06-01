package com.wiselap.accounts.Personal;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class PersonalPresenter<V extends PersonalContract.View> extends BasePresenterImpl<V> implements PersonalContract.Presenter{

    @Inject
    public PersonalPresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }

    @Override
    public void sendData(String name, String contact, String address) {

    }
}
