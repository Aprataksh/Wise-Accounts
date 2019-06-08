package com.wiselap.accounts.Office;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.Personal.ApiInterface;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.constants.ResponseCode;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.model.EntityModel;
import com.wiselap.accounts.model.OfficeEntity;
import com.wiselap.accounts.model.PersonalEntity;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.PreferenceUtils;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class OfficePresenter<V extends OfficeContract.View> extends BasePresenterImpl<V> implements OfficeContract.Presenter {

    private ServiceProvider<ApiInterface> serviceProvider;
    private final String TAG = OfficePresenter.class.getSimpleName();
    @Inject
    public OfficePresenter(SchedulerProvider scheduler, ServiceProvider<ApiInterface> serviceProvider, CompositeDisposable disposable, PreferenceUtils preferenceUtils) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider = serviceProvider;
    }

    @Override
    public void sendData(String office, String contact, String owner, String address) {

        OfficeEntity officeEntity = new OfficeEntity(office, contact, owner, address);
        officeEntity.setApplicationUserId(getPreferenceUtils().getApplicationUSerId());
        ///Log.d(TAG, "hjsgfahsgf" + new Gson().toJson(officeEntity));

        getDisposable().add(serviceProvider.getWrappedService().sendOfficeEntity(officeEntity)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<EntityModel>>() {
                    @Override
                    public void onNext(WrappedResponse<EntityModel> entityModelWrappedResponse) {
                        //Log.d(TAG, "hjsgfahsgf" + new Gson().toJson(entityModelWrappedResponse));
                        if(entityModelWrappedResponse.getMeta().getId() == ResponseCode.SUCCESS) {
                            getView().showMessage("Entity has been added");
                            getPreferenceUtils().saveAccountingProfileId(entityModelWrappedResponse.getData().getAccountingProfileId());
                        }
                        else
                            getView().showMessage("Something went wrong");
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showMessage("Something went wrong");
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public String getEmailId() {
        return getPreferenceUtils().getEmailId();
    }
}
