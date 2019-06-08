package com.wiselap.accounts.base_class;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.wiselap.accounts.R;
import com.wiselap.accounts.utils.AppUtils;
import com.wiselap.accounts.interfaces.BaseView;

import dagger.android.support.DaggerAppCompatActivity;


public class BaseActivity extends DaggerAppCompatActivity implements View.OnClickListener, BaseView {

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
    }

    @Override
    public void showLoadingDialog() {
        AppUtils.cancelLoadingDialog();
        AppUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoadingDialog() {
        AppUtils.cancelLoadingDialog();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showText(Context context, String str) {
        FancyToast.makeText(context, str, FancyToast.LENGTH_LONG, FancyToast.CONFUSING, R.drawable.ic_check_black_24dp, false);
    }


    public void askPersmissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

    }

    @Override
    public void onClick(View v) {

    }
}
