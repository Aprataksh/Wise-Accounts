package com.wiselap.accounts.di.component;



import com.wiselap.accounts.Configuration.ConfigurationActivity;
import com.wiselap.accounts.Configuration.ConfigurationContract;
import com.wiselap.accounts.ExpenseType.AddExpenseType.AddExpenseTypeActivity;
import com.wiselap.accounts.ExpenseType.ExpenseTypeActivity;
import com.wiselap.accounts.Personal.PersonalActivity;
import com.wiselap.accounts.Office.OfficeActivity;
import com.wiselap.accounts.Report.Expense_Report.ExpenseReportDisplay.ExpenseReportDisplayActivity;
import com.wiselap.accounts.Report.Expense_Report.ExpenseReportSearch.ExpenseReportActivity;
import com.wiselap.accounts.Report.ReportActivity;
import com.wiselap.accounts.Report.Salary_Report.SalaryReportDisplay.SalaryReportDisplayActivity;
import com.wiselap.accounts.Report.Salary_Report.SalaryReportSearch.SalaryReportActivity;
import com.wiselap.accounts.Select_Account.SelectAccount;
import com.wiselap.accounts.Select_Entity.SelectEntity;
import com.wiselap.accounts.expense.Expenses;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

@ContributesAndroidInjector
    abstract SelectEntity bindsSelectEntity();
@ContributesAndroidInjector
    abstract SelectAccount bindsSelectAccount();
@ContributesAndroidInjector
    abstract ReportActivity bindsReportActivity();
@ContributesAndroidInjector
    abstract ExpenseReportActivity bindsExpenseReportActivity();
@ContributesAndroidInjector
    abstract ExpenseReportDisplayActivity bindsExpenseReportDisplayActivity();
@ContributesAndroidInjector
    abstract OfficeActivity bindsOfficeActivity();
@ContributesAndroidInjector
    abstract PersonalActivity bindsPersonalActivity();
@ContributesAndroidInjector
    abstract Expenses bindsExpenses();
@ContributesAndroidInjector
    abstract SalaryReportActivity bindsSalaryReportActivity();
@ContributesAndroidInjector
    abstract SalaryReportDisplayActivity bindsSalaryReportDisplayActivity();
@ContributesAndroidInjector
    abstract ConfigurationActivity bindsConfigurationActivity();
@ContributesAndroidInjector
    abstract ExpenseTypeActivity bindsExpenseTypeActivity();
@ContributesAndroidInjector
    abstract AddExpenseTypeActivity bindsAddExpenseTypeActivity();

}
