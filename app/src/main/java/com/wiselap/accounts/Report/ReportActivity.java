package com.wiselap.accounts.Report;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wiselap.accounts.R;
import com.wiselap.accounts.Report.Expense_Report.ExpenseReportSearch.ExpenseReportActivity;
import com.wiselap.accounts.Report.Salary_Report.SalaryReportSearch.SalaryReportActivity;
import com.wiselap.accounts.adapters.ReportViewAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.databinding.ActivityReportBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ReportActivity extends BaseActivity implements ReportContract.View, ReportViewAdapter.OnReportListener {

    final int EXPENSE_REPORT = 0;
    final int SALARY_REPORT = 1;

    @Inject
    ReportPresenter<ReportContract.View> mPresenter;

    ActivityReportBinding report_binding;

    RecyclerView recyclerView;
    ReportViewAdapter adapter;
    List<String> type = new ArrayList<>();
    List<Integer> image = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        report_binding = DataBindingUtil.setContentView(this, R.layout.activity_report);

        //Setting Header
        report_binding.toolbar.title.setText(getString(R.string.Report));

        mPresenter.onAttach(this);

        initList();

        //Setting Adapter
        mPresenter.setAdapter();
    }


    public void onClick(View view){
        super.onClick(view);
        switch (view.getId()){
            case R.id.backBtn:
                finish();
                break;
        }
    }

    @Override
    public void createAdapter() {

        recyclerView = report_binding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new ReportViewAdapter(image, type, this, this);
        recyclerView.setAdapter(adapter);
    }

    //Setting List for RecyclerView
    private void initList(){
        type.add(getString(R.string.Expense_Report));
        image.add(R.drawable.expense_report_icon);

        type.add(getString(R.string.Salary_Report));
        image.add(R.drawable.salary_icon_16594);
    }

    // Function for Expense Report Activity
    public void setExpenseReport() {
        Intent intent = new Intent(this, ExpenseReportActivity.class);
        startActivity(intent);
        finish();
    }

    // Function for Salary Report Activity
    public void setSalaryReport() {
        startActivity(new Intent(this, SalaryReportActivity.class));
        finish();
    }

    @Override
    public void onReportClick(int position) {

        switch (position) {
            case EXPENSE_REPORT:
                setExpenseReport();
                break;
            case SALARY_REPORT:
                setSalaryReport();
        }
    }
}
