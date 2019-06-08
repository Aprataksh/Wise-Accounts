package com.wiselap.accounts.di.component;


import com.wiselap.accounts.Configuration.ConfigurationActivity;
import com.wiselap.accounts.ExpenseType.AddExpenseType.AddExpenseTypeActivity;
import com.wiselap.accounts.ExpenseType.AddExpenseType.ApiModule;
import com.wiselap.accounts.ExpenseType.ExpenseTypeActivity;
import com.wiselap.accounts.ExpenseType.ExpenseTypeModule;
import com.wiselap.accounts.Office.OfficeActivity;
import com.wiselap.accounts.Personal.PersonalActivity;
import com.wiselap.accounts.Report.Expense_Report.ExpenseReportDisplay.ExpenseReportDisplayActivity;
import com.wiselap.accounts.Report.Expense_Report.ExpenseReportSearch.ExpenseReportActivity;
import com.wiselap.accounts.Report.ReportActivity;
import com.wiselap.accounts.Report.Salary_Report.SalaryReportDisplay.SalaryReportDisplayActivity;
import com.wiselap.accounts.Report.Salary_Report.SalaryReportSearch.SalaryReportActivity;
import com.wiselap.accounts.Select_Account.SelectAccountActivity;
import com.wiselap.accounts.Select_Entity.SelectEntityActivity;
import com.wiselap.accounts.Select_Entity.SelectEntityModule;
import com.wiselap.accounts.expense.EditExpense;
import com.wiselap.accounts.expense.ExpenseModule;
import com.wiselap.accounts.expense.Expenses;
import com.wiselap.accounts.home_screen.Homepage;
import com.wiselap.accounts.users.UserModule;
import com.wiselap.accounts.users.Users;

import com.wiselap.accounts.SignIn.GoogleSignInUsersActivity;
import com.wiselap.accounts.SignIn.SignInModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SelectEntityModule.class)
    abstract SelectEntityActivity bindsSelectEntity();

    @ContributesAndroidInjector
    abstract SelectAccountActivity bindsSelectAccount();

    @ContributesAndroidInjector
    abstract ReportActivity bindsReportActivity();

    @ContributesAndroidInjector
    abstract ExpenseReportActivity bindsExpenseReportActivity();

    @ContributesAndroidInjector
    abstract Homepage bindsHomepage();

    @ContributesAndroidInjector(modules = ExpenseModule.class)
    abstract Expenses bindsExpenses();

    @ContributesAndroidInjector
    abstract EditExpense bindsEditxpense();

    @ContributesAndroidInjector(modules = SignInModule.class)
    abstract GoogleSignInUsersActivity bindsGoogleSignInUsers();

    @ContributesAndroidInjector(modules = UserModule.class)
    abstract Users bindsUsers();


    @ContributesAndroidInjector(modules = com.wiselap.accounts.Personal.ApiModule.class)
    abstract OfficeActivity bindsOfficeActivity();

    @ContributesAndroidInjector(modules = com.wiselap.accounts.Personal.ApiModule.class)
    abstract PersonalActivity bindsPersonalActivity();



    @ContributesAndroidInjector
    abstract ExpenseReportDisplayActivity bindsExpenseReportDisplayActivity();

    @ContributesAndroidInjector
    abstract SalaryReportActivity bindsSalaryReportActivity();
    @ContributesAndroidInjector
    abstract SalaryReportDisplayActivity bindsSalaryReportDisplayActivity();
    @ContributesAndroidInjector
    abstract ConfigurationActivity bindsConfigurationActivity();
    @ContributesAndroidInjector(modules = ExpenseTypeModule.class)
    abstract ExpenseTypeActivity bindsExpenseTypeActivity();
    @ContributesAndroidInjector(modules = ApiModule.class)
    abstract AddExpenseTypeActivity bindsAddExpenseTypeActivity();




}
