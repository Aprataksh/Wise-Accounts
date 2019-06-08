package com.wiselap.accounts.users;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.retrofit.WrappedResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class UserPresenter<V extends UserContract.view> extends BasePresenterImpl<V> implements UserContract.presenter {
    ServiceProvider<UserRequest> serviceProvider;

    @Inject
    public UserPresenter(SchedulerProvider scheduler, ServiceProvider<UserRequest> serviceProvider, CompositeDisposable disposable) {
        super(scheduler, disposable);
        this.serviceProvider = serviceProvider;
    }


    @Override
    public void getUsers(UserMethodModel userMethodModel) {
        getDisposable().add(serviceProvider.getWrappedService().getUsers(userMethodModel)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<List<UserReturnModel>>>() {
                    @Override
                    public void onNext(WrappedResponse<List<UserReturnModel>> listWrappedResponse) {
                        getView().setUsers(listWrappedResponse.getData());
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

