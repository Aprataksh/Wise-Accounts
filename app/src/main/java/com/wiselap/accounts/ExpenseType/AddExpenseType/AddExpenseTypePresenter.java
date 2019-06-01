package com.wiselap.accounts.ExpenseType.AddExpenseType;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.model.ExpenseType;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class AddExpenseTypePresenter<V extends AddExpenseTypeContract.View> extends BasePresenterImpl<V> implements AddExpenseTypeContract.Presenter {


    @Inject
    public AddExpenseTypePresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }

    @Override
    public List<ExpenseType> fetchExpenseTypeList() {
        return null;
    }
}
