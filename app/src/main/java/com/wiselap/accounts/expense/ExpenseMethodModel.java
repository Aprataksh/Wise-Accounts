package com.wiselap.accounts.expense;

public class ExpenseMethodModel {
    String startingDate;
    String endingDate;

    public ExpenseMethodModel() {
        startingDate="";
        endingDate="";
    }

    public ExpenseMethodModel(String startingDate, String endingDate) {
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }
}
