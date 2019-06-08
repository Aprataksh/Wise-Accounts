package com.wiselap.accounts.Report.Salary_Report.SalaryReportSearch;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.wiselap.accounts.R;
import com.wiselap.accounts.Report.ReportActivity;
import com.wiselap.accounts.Report.Salary_Report.SalaryReportDisplay.SalaryReportDisplayActivity;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivitySalaryReportBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.inject.Inject;

public class SalaryReportActivity extends BaseActivity implements SalaryReportContract.View{

    final int THIS_DATE = 0;
    ActivitySalaryReportBinding binding;
    @Inject SalaryReportPresenter<SalaryReportContract.View> mPresenter;
    Calendar thisDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_salary_report);
        binding.toolbar.title.setText(getString(R.string.Salary_Report));

        mPresenter.onAttach(this);
        setDate();


    }
    /* Date Picker methods*/
    @Override
    protected Dialog onCreateDialog(int id) {
        DatePickerDialog fromDialog =  new DatePickerDialog(this, dateSetListener,
                thisDate.get(Calendar.YEAR), thisDate.get(Calendar.MONTH),
                thisDate.get(Calendar.DAY_OF_MONTH));
        fromDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
        return fromDialog;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog
            .OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            thisDate.set(Calendar.YEAR, year);
            thisDate.set(Calendar.MONTH, monthOfYear);
            thisDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateText(binding.calenderText, thisDate);
        }
    };


    private void setDate(){
        thisDate=Calendar.getInstance();
        updateText(binding.calenderText, thisDate);
    }

    private void updateText(EditText editText, Calendar calendar){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppConstants.DATE_FORMAT_MMMYYYY1, Locale.getDefault());
        editText.setText(simpleDateFormat.format(calendar.getTime()));
    }

    public void onClick(View view){
        super.onClick(view);
        switch (view.getId()){
            case R.id.backBtn:
                startActivity(new Intent(SalaryReportActivity.this, ReportActivity.class));
                finish();
                break;
            case R.id.calenderBtn:
            case R.id.calenderText:
                showDialog(THIS_DATE);
                break;
            case R.id.generateBtn:
                Intent intent = new Intent(SalaryReportActivity.this, SalaryReportDisplayActivity.class);
                intent.putExtra(AppConstants.This_Date, thisDate);
                startActivity(intent);
                break;
            case R.id.exportBtn:
                break;
            case R.id.exportMailBtn:
                break;
        }
}



}
