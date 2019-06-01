package com.wiselap.accounts.ExpenseType.AddExpenseType;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.model.ExpenseType;

import java.util.List;

public interface AddExpenseTypeContract {

    interface View extends BaseView{

    }
    interface Presenter{
        List<ExpenseType> fetchExpenseTypeList();
    }
}
