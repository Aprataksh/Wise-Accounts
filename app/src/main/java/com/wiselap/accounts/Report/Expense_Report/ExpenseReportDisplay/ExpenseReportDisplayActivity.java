package com.wiselap.accounts.Report.Expense_Report.ExpenseReportDisplay;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.ExpenseReportAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityExpenseReportDisplayBinding;
import com.wiselap.accounts.model.ExpenseReport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class ExpenseReportDisplayActivity extends BaseActivity implements ExpenseReportDisplayContract.View, ExpenseReportAdapter.OnExpenseReportListener {

    ActivityExpenseReportDisplayBinding display_binding;

    List<ExpenseReport> expenseList = new ArrayList<>();
    RecyclerView recyclerView;
    ExpenseReportAdapter adapter;


    Date startDate = new Date();
    Date endDate = new Date();

    @Inject ExpenseReportDisplayPresenter<ExpenseReportDisplayContract.View> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        display_binding = DataBindingUtil.setContentView(this, R.layout.activity_expense_report_display);

        //Setting Header
        display_binding.toolbar.title.setText(getString(R.string.Expense_Report));

        mPresenter.onAttach(this);

        //Setting Dates
        setDates();

        //Test Data
        expenseList.add(new ExpenseReport("Consumption", 28618));
        expenseList.add(new ExpenseReport("Withdrawal", 50000));
        expenseList.add(new ExpenseReport("Savings", 3000));

        //Setting Adapter
        mPresenter.setAdapter();

        //Setting Total Expense
        mPresenter.getTotalAmount(expenseList);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.backBtn:
                finish();
                break;
            case R.id.printBtn:
                break;
        }
    }

    void setDates(){
        try{
            startDate = ((Calendar) getIntent().getSerializableExtra(AppConstants.Start_Date)).getTime();
            endDate = ((Calendar) getIntent().getSerializableExtra(AppConstants.End_Date)).getTime();
        }catch (Exception e){
            showMessage(getString(R.string.Invalid_Date));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.DATE_FORMAT_DDMMMYY1, Locale.ENGLISH);

        display_binding.fromDate.setText(simpleDateFormat.format(startDate));
        display_binding.toDate.setText(simpleDateFormat.format(endDate));
    }
    @Override
    public void createAdapter() {
        recyclerView = display_binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpenseReportAdapter(expenseList, this, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTotalAmount(double amount) {
        display_binding.totalExpense.setText(String.valueOf(amount));
    }

    @Override
    public void onExpenseReportClick(int position) {
        showMessage("Clicked at " + position);
    }
}
