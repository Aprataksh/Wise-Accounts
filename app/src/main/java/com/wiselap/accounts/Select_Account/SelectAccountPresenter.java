package com.wiselap.accounts.Select_Account;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.model.Account;
import com.wiselap.accounts.utils.PreferenceUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SelectAccountPresenter<V extends SelectAccountContract.View>
        extends BasePresenterImpl<V> implements SelectAccountContract.Presenter {

    @Inject
    public SelectAccountPresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }

    @Override
    public List<Account> fetchAccounts() {
        return null;
    }

    @Override
    public void setAdapter() {
        getView().createAdapter();
    }
}
