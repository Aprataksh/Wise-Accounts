package com.wiselap.accounts.model;

public class ExpenseModel {

    private String expense_name;
    private String expense_type;
    private String amount;

    public ExpenseModel(String expense_name, String expense_type, String amount) {
        this.expense_name = expense_name;
        this.expense_type = expense_type;
        this.amount = amount;
    }

    public String getExpense_name() {
        return expense_name;
    }

    public String getExpense_type() {
        return expense_type;
    }

    public String getAmount() {
        return amount;
    }

}
