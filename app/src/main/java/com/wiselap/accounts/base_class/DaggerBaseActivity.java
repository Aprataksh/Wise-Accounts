package com.wiselap.accounts.base_class;


import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.wiselap.accounts.R;
import com.wiselap.accounts.utils.AppUtils;
import com.wiselap.accounts.interfaces.BaseView;

import dagger.android.support.DaggerAppCompatActivity;


import static android.content.pm.PackageManager.GET_META_DATA;


public abstract class DaggerBaseActivity extends DaggerAppCompatActivity implements View.OnClickListener, BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resetTitles();
    }

    protected void resetTitles() {
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), GET_META_DATA);
            if (info.labelRes != 0) {
                setTitle(info.labelRes);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backBtn:
                finish();
                AppUtils.givefinishEffect(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.backBtn:
                finish();
                AppUtils.givefinishEffect(this);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        AppUtils.givefinishEffect(this);
        super.onBackPressed();
    }

    public void askPersmissions(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE},1);
     }
}
