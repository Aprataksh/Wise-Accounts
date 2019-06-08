package com.wiselap.accounts.base_class;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;
import com.wiselap.accounts.R;
import com.wiselap.accounts.utils.AppUtils;
import com.wiselap.accounts.interfaces.BaseView;

import dagger.android.support.DaggerFragment;


public class BaseFragment extends DaggerFragment implements View.OnClickListener, BaseView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showLoadingDialog() {
        AppUtils.showLoadingDialog(getActivity());
    }

    @Override
    public void hideLoadingDialog() {
        AppUtils.cancelLoadingDialog();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showText(Context context, String str) {
        FancyToast.makeText(context, str, FancyToast.LENGTH_LONG, FancyToast.CONFUSING, R.drawable.ic_check_black_24dp, false);
    }
}
