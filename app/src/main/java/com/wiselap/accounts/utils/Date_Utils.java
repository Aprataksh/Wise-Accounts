package com.wiselap.accounts.utils;

import android.util.Log;

import com.wiselap.accounts.constants.AppConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Date_Utils {

    private final static String TAG = Date_Utils.class.getSimpleName();
    public static String convertDate(String date, String fromFormat, String toFormat) {
        try {
            Log.d(TAG,"Checking date received in convert date "+date);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fromFormat, Locale.ENGLISH);
            SimpleDateFormat dateFormat = new SimpleDateFormat(toFormat, Locale.ENGLISH);
            java.util.Date parse = simpleDateFormat.parse(date);
            date = dateFormat.format(parse);
            Log.d(TAG,"Checking converted date  "+date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            Log.d(TAG,"Checking error in date  convert date "+e);
            e.printStackTrace();
        }

        return date;
    }


    public static String getCurrentDateWithTime() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat  = new SimpleDateFormat(AppConstants.Date_Format, Locale.ENGLISH);
        return simpleDateFormat.format(currentTime);
    }//getCurrentDateWithTime(Context context) closed ...

    public static String addPrefixBeforeDateNumber(int value) {
        return value < 10 ? "0" + value : String.valueOf(value);
    }

    public static String getCurrentDate(String format){
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static String getFirstDateOfPreviousMonth(){
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.MONTH, -1);
        calender.set(Calendar.DATE, 1);
        return df.format(calender.getTime());
    }

    public static String getTomorrowsDate(String format){
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
        Calendar calender = Calendar.getInstance();
        int day=calender.get(Calendar.DATE)+ 1;
        calender.set(Calendar.DATE,day);
        return df.format(calender.getTime());
    }

    public static String getLastDateOfPreviousMonth() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.MONTH, -1);
        calender.set(Calendar.DATE, calender.getActualMaximum(Calendar.DAY_OF_MONTH));
        return df.format(calender.getTime());
    }

}

