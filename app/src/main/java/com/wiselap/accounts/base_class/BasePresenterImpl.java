package com.wiselap.accounts.base_class;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.interfaces.BaseePresenter;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.utils.PreferenceUtils;

import io.reactivex.disposables.CompositeDisposable;


public class BasePresenterImpl<V extends BaseView> implements BaseePresenter<V> {

    private static final String TAG = "BasePresenter";

    private SchedulerProvider scheduler;
    private  CompositeDisposable disposable;
    private PreferenceUtils preferenceUtils;
    private V view;

    public BasePresenterImpl(SchedulerProvider scheduler, CompositeDisposable disposable) {
        this.scheduler = scheduler;
        this.disposable = disposable;
    }

    public BasePresenterImpl(SchedulerProvider scheduler, CompositeDisposable disposable, PreferenceUtils preferenceUtils) {
        this.scheduler = scheduler;
        this.disposable = disposable;
        this.preferenceUtils = preferenceUtils;
    }


    @Override
    public void onAttach(V view) {
        this.view = view;
    }

    @Override
    public void onDestroy() {
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    public V getView() { return view; }
    public SchedulerProvider getSchedulerProvider() {
        return scheduler;
    }
    public CompositeDisposable getDisposable() {
        return disposable;
    }

    public PreferenceUtils getPreferenceUtils() {
        return preferenceUtils;
    }
}
