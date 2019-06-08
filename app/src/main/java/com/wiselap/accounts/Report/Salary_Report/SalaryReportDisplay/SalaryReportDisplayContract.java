package com.wiselap.accounts.Report.Salary_Report.SalaryReportDisplay;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.model.SalaryReport;

import java.util.List;

public interface SalaryReportDisplayContract {
    interface View extends BaseView {
        void createAdapter();
        void setTotalSalary(double salary);
    }
    interface Presenter{
        void setAdapter();
        void getTotalSalary(List<SalaryReport> salaryReports);
    }
}
