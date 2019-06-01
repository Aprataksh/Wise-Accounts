package com.wiselap.accounts.ExpenseType;

import com.wiselap.accounts.interfaces.BaseView;

public interface ExpenseTypeContract {

    interface View extends BaseView{
        void createAdapter();
    }
    interface Presenter {
        void setAdapter();
    }
}
