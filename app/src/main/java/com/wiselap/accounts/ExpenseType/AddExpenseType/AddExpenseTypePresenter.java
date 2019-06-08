package com.wiselap.accounts.ExpenseType.AddExpenseType;

import android.util.Log;

import com.google.gson.Gson;
import com.wiselap.accounts.RequestCode;
import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.constants.ResponseCode;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.interfaces.ServiceProvider;
import com.wiselap.accounts.model.ConfExpenseType;
import com.wiselap.accounts.model.ExpenseType;
import com.wiselap.accounts.retrofit.WrappedResponse;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class AddExpenseTypePresenter<V extends AddExpenseTypeContract.View> extends BasePresenterImpl<V> implements AddExpenseTypeContract.Presenter {
    ServiceProvider<ApiInterface> serviceProvider;
    private final String TAG = AddExpenseTypePresenter.class.getSimpleName();


    @Inject
    public AddExpenseTypePresenter(SchedulerProvider scheduler, ServiceProvider<ApiInterface> serviceProvider, CompositeDisposable disposable, PreferenceUtils preferenceUtils) {
        super(scheduler, disposable, preferenceUtils);
        this.serviceProvider = serviceProvider;
    }

    @Override
    public List<ExpenseType> fetchExpenseTypeList() {
        return null;
    }

    @Override
    public void fetchExpenseType(String expenseType) {


        ConfExpenseType confExpenseType = new ConfExpenseType(expenseType, getPreferenceUtils().getApplicationUSerId());
        //Log.d(TAG, "hjsgfkja" + new Gson().toJson(confExpenseType));
        getDisposable().add(serviceProvider.getWrappedService()
                .getConfigurationExpenseTypes(confExpenseType)
                .subscribeOn(getSchedulerProvider().getIoScheduler())
                .observeOn(getSchedulerProvider().getUiScheduler())
                .subscribeWith(new DisposableObserver<WrappedResponse<ExpenseType>>() {
                    @Override
                    public void onNext(WrappedResponse<ExpenseType> itemWrappedResponse) {
                        getView().showLoadingDialog();
                        //Log.d(TAG, "hjsgfkja" + new Gson().toJson(itemWrappedResponse));
                        if(itemWrappedResponse.getMeta().getId() == RequestCode.SUCCESS)
                            getView().setFields(itemWrappedResponse.getData());
                        else if(itemWrappedResponse.getMeta().getId() == ResponseCode.NO_DATA_FOUND)
                            getView().showMessage("No such entry");
                        else {
                            getView().showMessage("Something went wrong");
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        getView().showMessage("Something went wrong");
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoadingDialog();
                    }
                }));
    }

    @Override
    public void addExpenseType(ExpenseType expenseType) {
        expenseType.setApplicationUserId(getPreferenceUtils().getApplicationUSerId());
        Log.d("TAG","hjsgfkja"+new Gson().toJson(expenseType));
        getDisposable().add(serviceProvider.getWrappedService().addConfigurationExpenseTypes(expenseType)
        .subscribeOn(getSchedulerProvider().getIoScheduler())
        .observeOn(getSchedulerProvider().getUiScheduler())
        .subscribeWith(new DisposableObserver<WrappedResponse<ExpenseType>>() {
            @Override
            public void onNext(WrappedResponse<ExpenseType> expenseTypeWrappedResponse) {
                getView().showLoadingDialog();
                if(expenseTypeWrappedResponse.getMeta().getId() == RequestCode.SUCCESS)
                    getView().showMessage("Expense Type has been added");
                else
                    getView().showMessage("Not added");

            }

            @Override
            public void onError(Throwable e) {
                //getView().hideLoadingDialog();
                getView().showMessage(e.getMessage());
                //e.printStackTrace();
            }

            @Override
            public void onComplete() {
                getView().hideLoadingDialog();
            }
        }));
    }

    @Override
    public void updateExpenseType(ExpenseType expenseType) {
        //Log.d(TAG, "hjsgfkja" + new Gson().toJson(expenseType));
        expenseType.setApplicationUserId(getPreferenceUtils().getApplicationUSerId());
        getDisposable().add(serviceProvider.getWrappedService().updateConfigurationExpenseTypes(expenseType)
           .subscribeOn(getSchedulerProvider().getIoScheduler())
           .observeOn(getSchedulerProvider().getUiScheduler())
           .subscribeWith(new DisposableObserver<WrappedResponse<ExpenseType>>() {
               @Override
               public void onNext(WrappedResponse<ExpenseType> expenseTypeWrappedResponse) {
                   getView().showLoadingDialog();
                   //Log.d(TAG, "hjsgfkja" + new Gson().toJson(expenseTypeWrappedResponse));
                   if(expenseTypeWrappedResponse.getMeta().getId() == ResponseCode.SUCCESS)
                       getView().showMessage("Updated");
                   else
                       getView().showMessage("Something went wrong");
               }

               @Override
               public void onError(Throwable e) {
                   //getView().hideLoadingDialog();
                   getView().showMessage("Something went wrong");

               }

               @Override
               public void onComplete() {
                   getView().hideLoadingDialog();
               }
           }));
   }


}
