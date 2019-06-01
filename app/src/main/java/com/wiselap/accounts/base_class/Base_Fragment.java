package com.wiselap.accounts.base_class;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wiselap.accounts.utils.AppUtils;
import com.wiselap.accounts.interfaces.BaseView;

import dagger.android.support.DaggerFragment;

public abstract class Base_Fragment extends DaggerFragment implements View.OnClickListener, BaseView {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showLoadingDialog() {
        AppUtils.cancelLoadingDialog();
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
}
