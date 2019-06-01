package com.wiselap.accounts.expense;

import android.view.View;

import com.wiselap.accounts.interfaces.BaseView;

public interface ExpensesContract {

    interface View extends BaseView{
        void createAdapter();
        void expenseEdit(android.view.View view, int i);
    }
    interface Presenter{
        void setAdapter();
    }
}
