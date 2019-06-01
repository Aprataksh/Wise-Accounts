package com.wiselap.accounts.Select_Entity;

import android.widget.ListView;

import com.wiselap.accounts.interfaces.BaseView;

public interface SelectEntityContract {
    interface SEView extends BaseView {
        void showMessage(String s);
        void createAdapter();
    }
    interface SEPresnter{
        void setAdapter();
    }
}
