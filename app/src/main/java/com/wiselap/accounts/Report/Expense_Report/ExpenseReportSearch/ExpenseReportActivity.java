package com.wiselap.accounts.Report.Expense_Report.ExpenseReportSearch;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.wiselap.accounts.R;
import com.wiselap.accounts.Report.Expense_Report.Calender.CalenderActivity;
import com.wiselap.accounts.Report.Expense_Report.ExpenseReportDisplay.ExpenseReportDisplayActivity;
import com.wiselap.accounts.Report.ReportActivity;
import com.wiselap.accounts.base_class.BaseActivity;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityExpenseReportBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class ExpenseReportActivity extends BaseActivity implements ExpenseReportContract.View {



    final int FROM = 0;
    final int TO = 1;
    final int REQUEST_CODE_CALENDAR = 0;
    private Calendar activeDate, startDate, endDate;
    private EditText activeText;

    ActivityExpenseReportBinding expense_binding;
    @Inject
    ExpenseReportPresenter<ExpenseReportContract.View> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        expense_binding = DataBindingUtil.setContentView(this,
                R.layout.activity_expense_report);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        //Setting Header
        expense_binding.toolbar.title.setText(getString(R.string.Expense_Report));

        mPresenter.onAttach(this);

        //Setting Dates
        setDates();

        //Setting Texts
        updateText(expense_binding.calender1Text, startDate);
        updateText(expense_binding.calender2Text, endDate);
    }

                                         /* Date Picker methods*/
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case FROM:
                DatePickerDialog fromDialog =  new DatePickerDialog(this, dateSetListener,
                        startDate.get(Calendar.YEAR), startDate.get(Calendar.MONTH),
                        startDate.get(Calendar.DAY_OF_MONTH));
                fromDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
                return fromDialog;
            case TO:
                DatePickerDialog toDialog =  new DatePickerDialog(this, dateSetListener,
                        endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH),
                        endDate.get(Calendar.DAY_OF_MONTH));
                toDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
                return toDialog;

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog
            .OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            activeDate.set(Calendar.YEAR, year);
            activeDate.set(Calendar.MONTH, monthOfYear);
            activeDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateText(activeText, activeDate);
            checkValid(activeText);
            activeDate=null;
            activeText=null;
        }
    };


    private void setDates(){
        startDate=Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);
        endDate=Calendar.getInstance();

    }

    private void activeDatePicker(EditText editText, Calendar calendar){
        activeDate=calendar;
        activeText=editText;
    }

    private void updateText(EditText editText, Calendar calendar){
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if(month.length()==1)
            month = "0"+month;
        if(day.length()==1)
            day = "0"+day;
        editText.setText(new StringBuilder().append(day).append("-")
                .append(month).append("-").append(year));
    }


    //Check whether start date < end date
    private void checkValid(EditText editText){
        if(startDate.getTimeInMillis() > endDate.getTimeInMillis()) {
            if(editText == expense_binding.calender1Text) {
                editText = expense_binding.calender2Text;
                editText.setText("");
                editText.setHint(getString(R.string.Set_to_date));
            }
            else {
                editText = expense_binding.calender1Text;
                editText.setText("");
                editText.setHint(getString(R.string.Set_from_date));
            }
        }
    }

                            /* Getting Data from Calender Activity*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String sDate = "", eDate = "";

        if(requestCode == REQUEST_CODE_CALENDAR){
            if(resultCode == Activity.RESULT_OK){
                try {
                    Date start_Date = (Date) data.getSerializableExtra(AppConstants.Start_Date);
                    Date end_Date = (Date) data.getSerializableExtra(AppConstants.End_Date);

                    startDate.setTime(start_Date);
                    endDate.setTime(end_Date);

                    sDate = new SimpleDateFormat(AppConstants.DATE_FORMAT_DDMMYYYY,
                            Locale.getDefault()).format(start_Date);
                    eDate = new SimpleDateFormat(AppConstants.DATE_FORMAT_DDMMYYYY,
                            Locale.getDefault()).format(end_Date);
                }
                catch (Exception e){
                    showMessage(getString(R.string.Invalid_Date));
                    finish();
                }
                expense_binding.calender1Text.setText(sDate);
                expense_binding.calender2Text.setText(eDate);
            }
        }
    }

                            /* All the clicks are handled here */
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            //Back Button
            case R.id.backBtn:
                startActivity(new Intent(ExpenseReportActivity.this,
                        ReportActivity.class));
                finish();
                break;

            //Calendar Buttons for Calendar Activity
            case R.id.calender2Btn:
            case R.id.calender1Btn:
                Intent returnIntent = new Intent(ExpenseReportActivity.this,
                        CalenderActivity.class);
                returnIntent.putExtra(AppConstants.Start_Date, startDate);
                returnIntent.putExtra(AppConstants.End_Date, endDate);
                startActivityForResult(returnIntent, REQUEST_CODE_CALENDAR);
                break;

            //Calender EditTexts for DatePicker
            case R.id.calender1Text:
                showDialog(FROM);
                activeDatePicker(expense_binding.calender1Text, startDate);
                break;
            case R.id.calender2Text:
                showDialog(TO);
                activeDatePicker(expense_binding.calender2Text, endDate);
                break;

            //Generate Button to next Activity
            case R.id.generateBtn:
                mPresenter.getData(startDate, endDate);
                Intent intent = new Intent(ExpenseReportActivity.this,
                        ExpenseReportDisplayActivity.class);
                intent.putExtra(AppConstants.Start_Date, startDate);
                intent.putExtra(AppConstants.End_Date, endDate);
                startActivity(intent);
                break;
            case R.id.exportBtn:
                break;
            case R.id.exportMailBtn:
                break;
        }
    }
}
