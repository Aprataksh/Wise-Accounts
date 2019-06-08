package com.wiselap.accounts.Report.Expense_Report.Calender;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.savvi.rangedatepicker.CalendarPickerView;
import com.wiselap.accounts.R;
import com.wiselap.accounts.constants.AppConstants;
import com.wiselap.accounts.databinding.ActivityCalenderBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalenderActivity extends AppCompatActivity {

    ActivityCalenderBinding calender_binding;
    CalendarPickerView calender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calender_binding = DataBindingUtil.setContentView(this, R.layout.activity_calender);

        //Setting Header
        calender_binding.toolbar.title.setText(getString(R.string.Calendar));

        Intent thisIntent = getIntent();
        List<Date> listDate = new ArrayList<>();

        Calendar start_Date = Calendar.getInstance();
        Calendar end_Date = Calendar.getInstance();
        try{
            start_Date = (Calendar) thisIntent.getSerializableExtra(AppConstants.Start_Date);
            end_Date = (Calendar) thisIntent.getSerializableExtra(AppConstants.End_Date);

            listDate.add(start_Date.getTime());
            listDate.add(end_Date.getTime());
        }catch (Exception e){
            Toast.makeText(this, getString(R.string.Invalid_Date), Toast.LENGTH_SHORT).show();
            finish();
        }
        Calendar thisDate = Calendar.getInstance();
        start_Date.add(Calendar.YEAR, -4);
        thisDate.add(Calendar.DAY_OF_MONTH, 1);

        calender = calender_binding.calenderView;
        calender.init(start_Date.getTime(), thisDate.getTime(),
                new SimpleDateFormat(AppConstants.DATE_FORMAT_MMMYYYY, Locale.getDefault()))
                .inMode(CalendarPickerView.SelectionMode.RANGE)
                .withSelectedDates(listDate);
        calender.scrollToDate(listDate.get(0));

    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.backBtn:
                finish();
                break;
            case R.id.submitBtn:
                List<Date> arrayList= calender.getSelectedDates();

                Date startDate = arrayList.get(0);
                Date endDate = arrayList.get(arrayList.size() - 1);

                Intent intent = new Intent();
                intent.putExtra(AppConstants.Start_Date, startDate);
                intent.putExtra(AppConstants.End_Date, endDate);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }

}
