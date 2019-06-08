package com.wiselap.accounts.Report.Expense_Report.ExpenseReportDisplay;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.model.ExpenseReport;

import java.util.List;

public interface ExpenseReportDisplayContract {

    interface View extends BaseView {
        void createAdapter();
        void setTotalAmount(double amount);
    }
    interface Presenter {
        void setAdapter();
        void getTotalAmount(List<ExpenseReport> expenseList);
    }
}
