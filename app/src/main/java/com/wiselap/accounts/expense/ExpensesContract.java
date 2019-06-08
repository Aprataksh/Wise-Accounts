package com.wiselap.accounts.expense;

import android.view.View;

import com.wiselap.accounts.interfaces.BaseView;

import java.util.List;

public interface ExpensesContract {

    interface View extends BaseView{
        void expenseEdit(android.view.View view, int i);
        void delete(int i);
        void setExpense(List<ExpenseReturnModel> list);
    }
    interface Presenter{
        void getExpense(ExpenseMethodModel expenseMethodModel);
    }
}
