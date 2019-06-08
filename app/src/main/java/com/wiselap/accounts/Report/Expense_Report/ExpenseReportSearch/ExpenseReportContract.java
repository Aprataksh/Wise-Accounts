package com.wiselap.accounts.Report.Expense_Report.ExpenseReportSearch;

import com.wiselap.accounts.interfaces.BaseView;

import java.util.Calendar;

public interface ExpenseReportContract {
    interface View extends BaseView {

    }
    interface Presenter{
        void getData(Calendar startDate, Calendar endDate);
    }
}
