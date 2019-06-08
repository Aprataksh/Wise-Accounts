package com.wiselap.accounts.Report.Salary_Report.SalaryReportDisplay;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wiselap.accounts.R;
import com.wiselap.accounts.adapters.SalaryReportAdapter;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivitySalaryReportDisplayBinding;
import com.wiselap.accounts.model.SalaryReport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class SalaryReportDisplayActivity extends BaseActivity implements SalaryReportDisplayContract.View {

    @Inject SalaryReportDisplayPresenter<SalaryReportDisplayContract.View> mPresenter;
    ActivitySalaryReportDisplayBinding binding;
    RecyclerView recyclerView;

    Date thisDate = new Date();
    List<SalaryReport> salaryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_salary_report_display);

        binding.toolbar.title.setText(getString(R.string.Salary_Report));

        setMonth();

        salaryList.add(new SalaryReport("Prakash Jha", 1000));
        salaryList.add(new SalaryReport("Shyam Sharma", 20000));

        mPresenter.onAttach(this);
        mPresenter.setAdapter();
        mPresenter.getTotalSalary(salaryList);
    }

    public void onClick(View v) {
        super.onClick(v);
        switch(v.getId()){
            case R.id.backBtn:
                finish();
                break;
            case R.id.printBtn:
                break;
        }
    }
    private void setMonth(){
        try {
            thisDate = ((Calendar) getIntent().getSerializableExtra(AppConstants.This_Date)).getTime();
        }catch (Exception e){
            showMessage(getString(R.string.Invalid_Date));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.DATE_FORMAT_MMMYYYY1, Locale.getDefault());
        binding.thisDate.setText(simpleDateFormat.format(thisDate));
    }

    @Override
    public void createAdapter() {
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SalaryReportAdapter adapter = new SalaryReportAdapter(salaryList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setTotalSalary(double salary) {
        binding.totalSalary.setText(String.valueOf(salary));
    }
}
