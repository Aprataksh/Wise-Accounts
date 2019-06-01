package com.wiselap.accounts.Report.Expense_Report.ExpenseReportDisplay;

import com.wiselap.accounts.base_class.BasePresenterImpl;
import com.wiselap.accounts.interfaces.SchedulerProvider;
import com.wiselap.accounts.model.ExpenseReport;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ExpenseReportDisplayPresenter<V extends ExpenseReportDisplayContract.View> extends BasePresenterImpl<V> implements ExpenseReportDisplayContract.Presenter {

    @Inject
    public ExpenseReportDisplayPresenter(SchedulerProvider scheduler, CompositeDisposable disposable) {
        super(scheduler, disposable);
    }

    @Override
    public void setAdapter() {
        getView().createAdapter();
    }

    @Override
    public void getTotalAmount(List<ExpenseReport> expenseList) {
        double sum=0;
        for(ExpenseReport val: expenseList)
            sum += val.getAmount();
        getView().setTotalAmount(sum);
    }
}
