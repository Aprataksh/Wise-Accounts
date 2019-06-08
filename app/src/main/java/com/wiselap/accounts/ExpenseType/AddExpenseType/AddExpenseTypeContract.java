package com.wiselap.accounts.ExpenseType.AddExpenseType;

import com.wiselap.accounts.interfaces.BaseView;
import com.wiselap.accounts.model.ExpenseType;

import java.util.List;

public interface AddExpenseTypeContract {

    interface View extends BaseView {
        void setFields(ExpenseType expenseType);
    }
    interface Presenter{
        List<ExpenseType> fetchExpenseTypeList();
        void fetchExpenseType(String expenseType);
        void addExpenseType(ExpenseType expenseType);
        void updateExpenseType(ExpenseType expenseType);
     }
}
