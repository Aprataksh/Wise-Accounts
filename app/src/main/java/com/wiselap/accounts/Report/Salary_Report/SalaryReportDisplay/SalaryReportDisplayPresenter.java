package com.wiselap.accounts.Report.Salary_Report.SalaryReportDisplay;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.model.SalaryReport;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SalaryReportDisplayPresenter<V extends SalaryReportDisplayContract.View> extends
        BasePresenterImpl<V> implements SalaryReportDisplayContract.Presenter {

    @Inject
    public SalaryReportDisplayPresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }

    @Override
    public void setAdapter() {
        getView().createAdapter();
    }

    @Override
    public void getTotalSalary(List<SalaryReport> salaryReports) {
        double sum = 0;
        for(SalaryReport val : salaryReports)
            sum += val.getAmount();
        getView().setTotalSalary(sum);
    }
}
