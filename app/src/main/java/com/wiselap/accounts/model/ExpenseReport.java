package com.wiselap.accounts.model;

public class ExpenseReport {
    private String expense_name;
    private double amount;

    public ExpenseReport(String expense_name, double amount){
        this.expense_name = expense_name;
        this.amount = amount;
    }

    public String getExpense_name(){ return this.expense_name;}
    public double getAmount(){ return this.amount;}

}
