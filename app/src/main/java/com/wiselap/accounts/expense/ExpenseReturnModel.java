package com.wiselap.accounts.expense;

public class ExpenseReturnModel {
    String Expense_name;
    String Remarks;
    String Date;
    Long expenseId;
    String ExpenseTypeName;
    Long Expense_amount;

    public ExpenseReturnModel(String expense_name, String remarks, String date, Long expenseId, String expenseTypeName, Long expense_amount) {
        Expense_name = expense_name;
        Remarks = remarks;
        Date = date;
        this.expenseId = expenseId;
        ExpenseTypeName = expenseTypeName;
        Expense_amount = expense_amount;
    }

    public String getExpense_name() {
        return Expense_name;
    }

    public String getRemarks() {
        return Remarks;
    }

    public String getDate() {
        return Date;
    }

    public long getExpenseId() {
        return expenseId;
    }

    public String getExpenseTypeName() {
        return ExpenseTypeName;
    }

    public String getExpense_amount() {
        return ""+Expense_amount;
    }
}
