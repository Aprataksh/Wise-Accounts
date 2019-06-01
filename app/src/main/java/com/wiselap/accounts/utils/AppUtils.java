package com.wiselap.accounts.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.TextView;


import com.wiselap.accounts.R;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;



public class AppUtils {


    private static Dialog loadingdialog;
    private static int width;
    public  static  void showLoadingDialog(Context context){
        try {
            loadingdialog = new Dialog(context);
            loadingdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
             loadingdialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loadingdialog.setCanceledOnTouchOutside(false);
            loadingdialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void cancelLoadingDialog(){
        if (loadingdialog!=null && loadingdialog.isShowing())
        {
            loadingdialog.cancel();
        }
    }


    public static void giveIntentEffect(Context context) {

        Activity activity = (Activity) context;

        activity.overridePendingTransition(R.anim.intent_in_slide_in_left, R.anim.intent_in_slide_out_left);
    }//intentInEffect closes here....

    public static void givefinishEffect(Context context) {
        Activity activity = (Activity) context;

        activity.overridePendingTransition(R.anim.intent_out_slide_in_left, R.anim.intent_out_slide_out_left);
    }//givefinishEffect closes here....


    public static void showPopUp(Context context, TextView view, ArrayList al,
                                 ListPopupWindow window) {
        ArrayAdapter adapter = new ArrayAdapter<T>(context,
                R.layout.list_single_textview) {
            @Override
            public View getView(int position, View convertView,
                                ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                return view;
            }
        };
        adapter.addAll(al);
        window.setAdapter(adapter);
        window.setAnchorView(view);
        window.setWidth(view.getWidth());
//		window.setModal(true);
        //window.setHeight(screenHeight/2);
        window.setHeight(android.app.ActionBar.LayoutParams.WRAP_CONTENT);
        window.show();

    }


    public static int getWidth(Context context){
        width =context.getResources().getDisplayMetrics().widthPixels;
        return  width ;
    }
    public static int getHeight(Context context){
        return  context.getResources().getDisplayMetrics().heightPixels ;
    }




    public static String capitalizeFirstLetter(String inputString){
        return inputString.substring(0,1).toUpperCase() + inputString.substring(1);
    }


    public static String  getCurrentAppVersion(Context context){
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
