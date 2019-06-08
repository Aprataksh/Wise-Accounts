package com.wiselap.accounts.model;

public class ConfExpenseType {
    private String expenseType;
    private long applicationUserId;

    public ConfExpenseType(String expenseType, long applicationUserId) {
        this.expenseType = expenseType;
        this.applicationUserId = applicationUserId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }
}
