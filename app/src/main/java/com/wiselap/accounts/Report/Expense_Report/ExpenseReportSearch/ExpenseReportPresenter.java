package com.wiselap.accounts.Report.Expense_Report.ExpenseReportSearch;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;

import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ExpenseReportPresenter<V extends ExpenseReportContract.View> extends BasePresenterImpl<V> implements ExpenseReportContract.Presenter {


    @Inject
    public ExpenseReportPresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }

    @Override
    public void getData(Calendar startDate, Calendar endDate) {

    }
}
