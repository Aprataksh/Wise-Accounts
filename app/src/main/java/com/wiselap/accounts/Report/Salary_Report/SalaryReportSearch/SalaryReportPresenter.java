package com.wiselap.accounts.Report.Salary_Report.SalaryReportSearch;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SalaryReportPresenter<V extends SalaryReportContract.View> extends BasePresenterImpl<V> implements SalaryReportContract.Presenter {

    @Inject
    public SalaryReportPresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }
}
