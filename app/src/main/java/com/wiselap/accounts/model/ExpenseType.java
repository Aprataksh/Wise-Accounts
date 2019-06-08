package com.wiselap.accounts.model;

import com.google.gson.annotations.SerializedName;
import com.wiselap.accounts.constants.AppConstants;

public class ExpenseType {

    @SerializedName(AppConstants.expenseTypeId)
    private Integer id;

    @SerializedName(AppConstants.expenseType)
    private String expense_name;
    private String frequency;
    @SerializedName(AppConstants.defaultAmount)
    private double amount;

    private long applicationUserId;

    public ExpenseType(Integer id, String expense_name, String frequency, double amount) {
        this.expense_name = expense_name;
        this.id = id;
        this.frequency = frequency;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExpense_name() {
        return expense_name;
    }

    public void setExpense_name(String expense_name) {
        this.expense_name = expense_name;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(long applicationUserId) {
        this.applicationUserId = applicationUserId;
    }
}
