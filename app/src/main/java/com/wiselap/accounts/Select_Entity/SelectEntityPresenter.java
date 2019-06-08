package com.wiselap.accounts.Select_Entity;

import com.wiselap.accounts.RequestCode;
import com.wiselap.accounts.Select_Account.SelectAccountPresenter;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.model.Item;
import com.wiselap.accounts.retrofit.WrappedResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class SelectEntityPresenter<V extends SelectEntityContract.SEView> extends BasePresenterImpl<V> implements SelectEntityContract.SEPresnter{

    ServiceProvider<GetSelectEntity> serviceProvider;
    List<Item> item_list;
    private final String TAG = SelectAccountPresenter.class.getSimpleName();


    @Inject
    public SelectEntityPresenter(SchedulerProvider scheduler, ServiceProvider<GetSelectEntity> serviceProvider, CompositeDisposable disposable) {
        super(scheduler, disposable);
        this.serviceProvider = serviceProvider;
    }

    private void setItem_list(){
        item_list = new ArrayList<>();
        item_list.add(new Item("Office", 0));
        item_list.add(new Item("Personal", 1));
    }


    @Override
    public void setAdapter() {
        getView().showLoadingDialog();
        getDisposable().add(serviceProvider.getWrappedService().getEntityTypes()
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<List<Item>>>() {
                    @Override
                    public void onNext(WrappedResponse<List<Item>> listWrappedResponse) {
                        getView().showLoadingDialog();
                        if(listWrappedResponse.getMeta().getId() == RequestCode.SUCCESS)
                            getView().createAdapter(listWrappedResponse.getData());
                        else {
                            setItem_list();
                            getView().createAdapter(item_list);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoadingDialog();
                        getView().showMessage(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoadingDialog();
                    }
                }));
    }
}
